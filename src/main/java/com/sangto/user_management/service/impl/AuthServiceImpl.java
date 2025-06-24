package com.sangto.user_management.service.impl;

import com.sangto.user_management.domain.dto.auth.*;
import com.sangto.user_management.domain.entity.User;
import com.sangto.user_management.domain.enums.EUserRole;
import com.sangto.user_management.domain.mapper.UserMapper;
import com.sangto.user_management.exceptions.AppException;
import com.sangto.user_management.repository.UserRepository;
import com.sangto.user_management.responses.Response;
import com.sangto.user_management.service.AuthService;
import com.sangto.user_management.utility.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserMapper userMapper;

    @Override
    public Response<LoginResponseDTO> login(LoginRequestDTO requestDTO) {
        Optional<User> findUser = userRepository.findByEmailAndDeleted(requestDTO.email());

        if (findUser.isEmpty()) throw new AppException("Either email or password is incorrect");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(requestDTO.password(), findUser.get().getPassword());
        if (!authenticated) throw new AppException("Wrong password");

        String token = jwtTokenUtil.generateToken(findUser.get());

        LoginResponseDTO responseDTO = LoginResponseDTO.builder()
                .authenticated(true)
                .token(token)
                .info(userMapper.toUserDetailResponseDTO(findUser.get()))
                .build();

        return Response.successfulResponse("Login successful", responseDTO);
    }

    @Override
    public Response<RegisterUserResponseDTO> register(RegisterUserRequestDTO requestDTO) {
        Optional<User> findUser = userRepository.findByEmailAndDeleted(requestDTO.email());
        if (findUser.isPresent()) throw new AppException("This email is already in use");

        User newUser = userMapper.registerUserRequestDTOtoEntity(requestDTO);
        newUser.setRole(EUserRole.ROLE_USER);
        // Set password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        newUser.setPassword(passwordEncoder.encode(requestDTO.password()));

        try {
            User savedUser = userRepository.save(newUser);
            return Response.successfulResponse("Register successfully",
                    RegisterUserResponseDTO.builder()
                            .accessToken(jwtTokenUtil.generateToken(savedUser))
                            .userDetail(userMapper.toUserDetailResponseDTO(savedUser))
                            .build());
        } catch (Exception e) {
            throw new AppException("Register failed: " + e.getMessage());
        }
    }

    @Override
    public Response<String> changePassword(Integer userId, ChangePasswordRequestDTO requestDTO) {
        Optional<User> findUser = userRepository.findById(userId);
        if (findUser.isEmpty()) throw new AppException("User not found");

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(requestDTO.oldPassword(), findUser.get().getPassword());
        if (!authenticated) throw new AppException("Current password is incorrect");

        findUser.get().setPassword(passwordEncoder.encode(requestDTO.newPassword()));
        try {
            userRepository.save(findUser.get());
            return Response.successfulResponse("Password changed successfully");
        } catch (Exception e) {
            throw new AppException("Change password failed: " + e.getMessage());
        }
    }
}
