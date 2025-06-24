package com.sangto.user_management.domain.dto.auth;

public record RegisterUserRequestDTO(
        String username,
        String email,
        String password
) {
}
