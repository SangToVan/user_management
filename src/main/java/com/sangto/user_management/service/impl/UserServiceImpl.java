package com.sangto.user_management.service.impl;

import com.sangto.user_management.domain.dto.admin.AddUserRequestDTO;
import com.sangto.user_management.domain.dto.admin.UserDetailToAdminResponseDTO;
import com.sangto.user_management.domain.dto.user.UpdUserRequestDTO;
import com.sangto.user_management.domain.dto.user.UserDetailResponseDTO;
import com.sangto.user_management.domain.dto.user.UserResponseDTO;
import com.sangto.user_management.domain.entity.User;
import com.sangto.user_management.domain.enums.EUserRole;
import com.sangto.user_management.domain.mapper.UserMapper;
import com.sangto.user_management.exceptions.AppException;
import com.sangto.user_management.repository.UserRepository;
import com.sangto.user_management.responses.Response;
import com.sangto.user_management.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostConstruct
    public void init() {
        initializeAdmin();
    }

    @Override
    public void initializeAdmin() {
        Optional<User> findAdmin = userRepository.findFirstByRole(EUserRole.ROLE_ADMIN);
        if (findAdmin.isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@admin.com");
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(EUserRole.ROLE_ADMIN);
            admin.setCreatedAt(LocalDateTime.now());
            admin.setUpdatedAt(LocalDateTime.now());
            admin.setDeleted(false);
            userRepository.save(admin);
            log.warn("Admin user has been created with default password!");
        } else {
            log.warn("Admin user already exists!");
        }
        log.info("Admin user has been initialized!");
    }

    @Override
    public Response<UserResponseDTO> addUser(AddUserRequestDTO requestDTO) {
        Optional<User> findUser = userRepository.findByEmail(requestDTO.email());
        if (findUser.isPresent()) throw new AppException("Email already in use!");

        User newUser = userMapper.addUserRequestDTOtoEntity(requestDTO);
        // Set password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        try {
            User savedUser = userRepository.save(newUser);
            return Response.successfulResponse("Added user successfully!",
                    userMapper.toUserResponseDTO(savedUser));
        } catch (Exception e) {
            throw new AppException("Added user unsuccessfully" + e.getMessage());
        }
    }

    @Override
    public Response<UserDetailResponseDTO> detailUser(Integer id) {
        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isEmpty()) throw new AppException("User not found!");

        return Response.successfulResponse("Get detail user successfully!",
                userMapper.toUserDetailResponseDTO(findUser.get()));
    }

    @Override
    public Response<UserDetailToAdminResponseDTO> detailUserToAdmin(Integer id) {
        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isEmpty()) throw new AppException("User not found!");

        return Response.successfulResponse("Get detail user successfully!",
                userMapper.toUserDetailToAdminResponseDTO(findUser.get()));
    }

    @Override
    public Response<UserDetailResponseDTO> updateUser(Integer id, UpdUserRequestDTO requestDTO) {
        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isEmpty()) throw new AppException("User not found!");

        User oldUser = findUser.get();
        User newUser = userMapper.updUserRequestDTOtoEntity(oldUser, requestDTO);

        try {
            User savedUser = userRepository.save(newUser);
            return Response.successfulResponse("Update detail successfully",
                    userMapper.toUserDetailResponseDTO(savedUser));
        } catch (Exception e) {
            throw new AppException("Update user unsuccessfully" + e.getMessage());
        }
    }

    @Override
    public Response<UserDetailToAdminResponseDTO> adminUpdateUser(Integer id, UpdUserRequestDTO requestDTO) {
        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isEmpty()) throw new AppException("User not found!");

        User oldUser = findUser.get();
        User newUser = userMapper.updUserRequestDTOtoEntity(oldUser, requestDTO);

        try {
            User savedUser = userRepository.save(newUser);
            return Response.successfulResponse("Update detail successfully",
                    userMapper.toUserDetailToAdminResponseDTO(savedUser));
        } catch (Exception e) {
            throw new AppException("Update user unsuccessfully" + e.getMessage());
        }
    }

    @Override
    public Response<UserDetailToAdminResponseDTO> deleteUser(Integer id) {
        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isEmpty()) throw new AppException("User not found!");
        User user = findUser.get();
        if (user.isDeleted()) {
            throw new AppException("User has been deleted!");
        } else {
            user.setDeleted(true);
            User savedUser = userRepository.save(user);
            return Response.successfulResponse("Delete user successfully",
                    userMapper.toUserDetailToAdminResponseDTO(savedUser));
        }
    }

    @Override
    public Response<List<UserResponseDTO>> getAllUsers() {
        List<User> findAllUsers = userRepository.findAllExcludingRole(EUserRole.ROLE_ADMIN);
        if (findAllUsers.isEmpty()) throw new AppException("User not found!");
        List<UserResponseDTO> list = findAllUsers.stream().map(userMapper::toUserResponseDTO).toList();

        return Response.successfulResponse("Get all users successfully", list);
    }
}
