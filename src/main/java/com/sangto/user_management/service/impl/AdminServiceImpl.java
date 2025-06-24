package com.sangto.user_management.service.impl;

import com.sangto.user_management.domain.dto.admin.UserDetailToAdminResponseDTO;
import com.sangto.user_management.domain.entity.User;
import com.sangto.user_management.domain.enums.EUserRole;
import com.sangto.user_management.domain.mapper.UserMapper;
import com.sangto.user_management.exceptions.AppException;
import com.sangto.user_management.repository.UserRepository;
import com.sangto.user_management.responses.Response;
import com.sangto.user_management.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Response<UserDetailToAdminResponseDTO> changeRole(Integer userId, EUserRole role) {
        Optional<User> findUser = userRepository.findById(userId);
        if (findUser.isEmpty()) throw new AppException("User not found!");

        User user = findUser.get();
        user.setRole(role);
        User savedUser = userRepository.save(user);

        return Response.successfulResponse("Change role user successfully!",
                userMapper.toUserDetailToAdminResponseDTO(savedUser));
    }
}
