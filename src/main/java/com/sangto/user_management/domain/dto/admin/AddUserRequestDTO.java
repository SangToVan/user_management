package com.sangto.user_management.domain.dto.admin;

import com.sangto.user_management.domain.enums.EUserRole;

public record AddUserRequestDTO(
        String username,
        String email,
        String password,
        EUserRole role
) {
}
