package com.sangto.user_management.controller;

import com.sangto.user_management.constant.Endpoint;
import com.sangto.user_management.domain.dto.user.UpdUserRequestDTO;
import com.sangto.user_management.domain.dto.user.UserDetailResponseDTO;
import com.sangto.user_management.responses.Response;
import com.sangto.user_management.service.UserService;
import com.sangto.user_management.utility.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @Operation(summary = "View detail", description = "This API allows user to view detail")
    @GetMapping(Endpoint.V1.User.DETAIL)
    public ResponseEntity<Response<UserDetailResponseDTO>> getDetail(HttpServletRequest servletRequest) {
        Integer userId = Integer.valueOf(jwtTokenUtil.getAccountId(servletRequest.getHeader(HttpHeaders.AUTHORIZATION)));
        return ResponseEntity.status(HttpStatus.OK).body(userService.detailUser(userId));
    }

    @Operation(summary = "Update detail", description = "This API allows user to update detail")
    @PatchMapping(Endpoint.V1.User.DETAIL)
    public ResponseEntity<Response<UserDetailResponseDTO>> updateDetail(HttpServletRequest servletRequest,
                                                                        @RequestBody @Valid UpdUserRequestDTO requestDTO) {
        Integer userId = Integer.valueOf(jwtTokenUtil.getAccountId(servletRequest.getHeader(HttpHeaders.AUTHORIZATION)));
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId, requestDTO));
    }
}
