package com.sangto.user_management.controller;

import com.sangto.user_management.constant.Endpoint;
import com.sangto.user_management.domain.dto.auth.*;
import com.sangto.user_management.responses.Response;
import com.sangto.user_management.service.AuthService;
import com.sangto.user_management.utility.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtTokenUtil jwtTokenUtil;

    @Operation(summary = "Log in", description = "This API allows user to log in")
    @PostMapping(Endpoint.V1.Auth.LOGIN)
    public ResponseEntity<Response<LoginResponseDTO>> login(@RequestBody @Valid LoginRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(requestDTO));
    }

    @Operation(summary = "Sign up", description = "This API allows user to sign up")
    @PostMapping(Endpoint.V1.Auth.REGISTER)
    public ResponseEntity<Response<RegisterUserResponseDTO>> signup(@RequestBody @Valid RegisterUserRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.register(requestDTO));
    }

    @Operation(summary = "Change password", description = "This API allows user to change password")
    @PatchMapping(Endpoint.V1.Auth.CHANGE_PASSWORD)
    public ResponseEntity<Response<String>> changePassword(HttpServletRequest servletRequest, @RequestBody @Valid ChangePasswordRequestDTO requestDTO) {
        Integer userId = Integer.valueOf(jwtTokenUtil.getAccountId(servletRequest.getHeader(HttpHeaders.AUTHORIZATION)));
        return ResponseEntity.status(HttpStatus.OK).body(authService.changePassword(userId, requestDTO));
    }
}
