package com.sangto.user_management.domain.dto.admin;

import com.sangto.user_management.domain.enums.EUserRole;
import lombok.Builder;

@Builder
public record UserDetailToAdminResponseDTO(
        Integer id,
        String username,
        String email,
        String password,
        String phone,
        String avatar,
        boolean isDeleted,
        EUserRole role,
        String createdAt,
        String updatedAt) {
}
