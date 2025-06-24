package com.sangto.user_management.service;

import com.sangto.user_management.domain.dto.admin.UserDetailToAdminResponseDTO;
import com.sangto.user_management.domain.enums.EUserRole;
import com.sangto.user_management.responses.Response;

public interface AdminService {
    Response<UserDetailToAdminResponseDTO> changeRole(Integer userId, EUserRole role);
}
