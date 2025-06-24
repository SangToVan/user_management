package com.sangto.user_management.domain.dto.user;

import lombok.Builder;

@Builder
public record UserDetailResponseDTO(
        String username,
        String email,
        String password,
        String phone,
        String avatar,
        String createdAt
) {
}
