package com.sangto.user_management.service;

import com.sangto.user_management.domain.dto.auth.*;
import com.sangto.user_management.responses.Response;

public interface AuthService {
    Response<LoginResponseDTO> login(LoginRequestDTO requestDTO);

    Response<RegisterUserResponseDTO> register(RegisterUserRequestDTO requestDTO);

    Response<String> changePassword(Integer userId, ChangePasswordRequestDTO requestDTO);
}
