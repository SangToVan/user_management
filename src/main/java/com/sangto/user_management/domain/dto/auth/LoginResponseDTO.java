package com.sangto.user_management.domain.dto.auth;

import com.sangto.user_management.domain.dto.user.UserDetailResponseDTO;
import lombok.Builder;

@Builder
public record LoginResponseDTO(
        boolean authenticated,
        String token,
        UserDetailResponseDTO info
) {
}
