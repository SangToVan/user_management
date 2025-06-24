package com.sangto.user_management.domain.dto.auth;

public record ChangePasswordRequestDTO(
        String oldPassword,
        String newPassword
) {
}
