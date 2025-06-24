package com.sangto.user_management.controller;

import com.sangto.user_management.constant.Endpoint;
import com.sangto.user_management.domain.dto.admin.AddUserRequestDTO;
import com.sangto.user_management.domain.dto.admin.UserDetailToAdminResponseDTO;
import com.sangto.user_management.domain.dto.user.UpdUserRequestDTO;
import com.sangto.user_management.domain.dto.user.UserResponseDTO;
import com.sangto.user_management.domain.enums.EUserRole;
import com.sangto.user_management.responses.Response;
import com.sangto.user_management.service.AdminService;
import com.sangto.user_management.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Admin")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AdminController {
    private final UserService userService;
    private final AdminService adminService;

    @Operation(summary = "Add user", description = "This API allows admin to add user")
    @PostMapping(Endpoint.V1.Admin.USER)
    public ResponseEntity<Response<UserResponseDTO>> addUser(@RequestBody @Valid AddUserRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.addUser(requestDTO));
    }

    @Operation(summary = "Get list user", description = "This API allows admin to get list user")
    @GetMapping(Endpoint.V1.Admin.USER)
    public ResponseEntity<Response<List<UserResponseDTO>>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @Operation(summary = "View user detail", description = "This API allows admin to view user detail")
    @GetMapping(Endpoint.V1.Admin.USER_DETAIL)
    public ResponseEntity<Response<UserDetailToAdminResponseDTO>> detailUser(@PathVariable(name = "id") Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.detailUserToAdmin(userId));
    }

    @Operation(summary = "Update user detail", description = "This API allows admin to update user detail")
    @PatchMapping(Endpoint.V1.Admin.USER_DETAIL)
    public ResponseEntity<Response<UserDetailToAdminResponseDTO>> updateDetail(@PathVariable(name = "id") Integer userId, @RequestBody @Valid UpdUserRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.adminUpdateUser(userId, requestDTO));
    }

    @Operation(summary = "Delete user", description = "This API allows admin to delete user")
    @PatchMapping(Endpoint.V1.Admin.USER_DELETE)
    public ResponseEntity<Response<UserDetailToAdminResponseDTO>> deleteUser(@PathVariable(name = "id") Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(userId));
    }

    @Operation(summary = "Change role", description = "This API allows admin to change role user")
    @PatchMapping(Endpoint.V1.Admin.USER_ROLE)
    public ResponseEntity<Response<UserDetailToAdminResponseDTO>> changeRole(@PathVariable(name = "id") Integer userId, @RequestBody @Valid EUserRole role) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.changeRole(userId, role));
    }
}
