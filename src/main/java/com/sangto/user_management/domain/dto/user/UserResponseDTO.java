package com.sangto.user_management.domain.dto.user;

import com.sangto.user_management.domain.enums.EUserRole;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponseDTO(
        Integer id,
        String username,
        String email,
        String phone,
        boolean isDeleted,
        EUserRole role,
        LocalDateTime createdAt
) {
}
