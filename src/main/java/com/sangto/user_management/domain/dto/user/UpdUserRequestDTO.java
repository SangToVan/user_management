package com.sangto.user_management.domain.dto.user;

public record UpdUserRequestDTO(
        String username,
        String phone,
        String avatar
) {
}
