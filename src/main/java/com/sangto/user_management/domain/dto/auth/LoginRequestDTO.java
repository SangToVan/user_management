package com.sangto.user_management.domain.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "Email is required.")
        @Email(message = "Please enter a valid email address.")
        String email,
        @NotBlank(message = "Password is required.")
        String password
) {
}
