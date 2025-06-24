package com.sangto.user_management.domain.dto.auth;

import com.sangto.user_management.domain.dto.user.UserDetailResponseDTO;
import lombok.Builder;

@Builder
public record RegisterUserResponseDTO(
        String accessToken,
        UserDetailResponseDTO userDetail
) {
}
