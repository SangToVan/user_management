package com.sangto.user_management.domain.mapper;

import com.sangto.user_management.domain.dto.admin.AddUserRequestDTO;
import com.sangto.user_management.domain.dto.admin.UserDetailToAdminResponseDTO;
import com.sangto.user_management.domain.dto.auth.RegisterUserRequestDTO;
import com.sangto.user_management.domain.dto.user.UpdUserRequestDTO;
import com.sangto.user_management.domain.dto.user.UserDetailResponseDTO;
import com.sangto.user_management.domain.dto.user.UserResponseDTO;
import com.sangto.user_management.domain.entity.User;

public interface UserMapper {
    UserDetailResponseDTO toUserDetailResponseDTO(User entity);

    UserDetailToAdminResponseDTO toUserDetailToAdminResponseDTO(User entity);

    UserResponseDTO toUserResponseDTO(User entity);

    User registerUserRequestDTOtoEntity(RegisterUserRequestDTO requestDTO);

    User addUserRequestDTOtoEntity(AddUserRequestDTO requestDTO);

    User updUserRequestDTOtoEntity(User oldUser, UpdUserRequestDTO requestDTO);
}
