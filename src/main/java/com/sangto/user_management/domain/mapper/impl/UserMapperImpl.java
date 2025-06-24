package com.sangto.user_management.domain.mapper.impl;

import com.sangto.user_management.domain.dto.admin.AddUserRequestDTO;
import com.sangto.user_management.domain.dto.admin.UserDetailToAdminResponseDTO;
import com.sangto.user_management.domain.dto.auth.RegisterUserRequestDTO;
import com.sangto.user_management.domain.dto.user.UpdUserRequestDTO;
import com.sangto.user_management.domain.dto.user.UserDetailResponseDTO;
import com.sangto.user_management.domain.dto.user.UserResponseDTO;
import com.sangto.user_management.domain.entity.User;
import com.sangto.user_management.domain.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDetailResponseDTO toUserDetailResponseDTO(User entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        return UserDetailResponseDTO.builder()
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .phone(entity.getPhone())
                .avatar(entity.getAvatar())
                .createdAt(entity.getCreatedAt().format(formatter))
                .build();
    }

    @Override
    public UserDetailToAdminResponseDTO toUserDetailToAdminResponseDTO(User entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        return UserDetailToAdminResponseDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .phone(entity.getPhone())
                .avatar(entity.getAvatar())
                .isDeleted(entity.isDeleted())
                .role(entity.getRole())
                .createdAt(entity.getCreatedAt().format(formatter))
                .updatedAt(entity.getUpdatedAt().format(formatter))
                .build();
    }

    @Override
    public UserResponseDTO toUserResponseDTO(User entity) {
        return UserResponseDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .isDeleted(entity.isDeleted())
                .role(entity.getRole())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    @Override
    public User registerUserRequestDTOtoEntity(RegisterUserRequestDTO requestDTO) {
        return User.builder()
                .username(requestDTO.username())
                .email(requestDTO.email())
                .password(requestDTO.password())
                .isDeleted(false)
                .build();
    }

    @Override
    public User addUserRequestDTOtoEntity(AddUserRequestDTO requestDTO) {
        return User.builder()
                .username(requestDTO.username())
                .email(requestDTO.email())
                .password(requestDTO.password())
                .role(requestDTO.role())
                .isDeleted(false)
                .build();
    }

    @Override
    public User updUserRequestDTOtoEntity(User oldUser, UpdUserRequestDTO requestDTO) {
        oldUser.setUsername(requestDTO.username());
        oldUser.setPhone(requestDTO.phone());
        oldUser.setAvatar(requestDTO.avatar());

        return oldUser;
    }
}
