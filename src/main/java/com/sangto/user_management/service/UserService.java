package com.sangto.user_management.service;

import com.sangto.user_management.domain.dto.admin.AddUserRequestDTO;
import com.sangto.user_management.domain.dto.admin.UserDetailToAdminResponseDTO;
import com.sangto.user_management.domain.dto.user.UpdUserRequestDTO;
import com.sangto.user_management.domain.dto.user.UserDetailResponseDTO;
import com.sangto.user_management.domain.dto.user.UserResponseDTO;
import com.sangto.user_management.responses.Response;

import java.util.List;

public interface UserService {

    void initializeAdmin();

    Response<UserResponseDTO> addUser(AddUserRequestDTO requestDTO);

    Response<UserDetailResponseDTO> detailUser(Integer id);

    Response<UserDetailToAdminResponseDTO> detailUserToAdmin(Integer id);

    Response<UserDetailResponseDTO> updateUser(Integer id, UpdUserRequestDTO requestDTO);

    Response<UserDetailToAdminResponseDTO> adminUpdateUser(Integer id, UpdUserRequestDTO requestDTO);

    Response<UserDetailToAdminResponseDTO> deleteUser(Integer id);

    Response<List<UserResponseDTO>> getAllUsers();
}
