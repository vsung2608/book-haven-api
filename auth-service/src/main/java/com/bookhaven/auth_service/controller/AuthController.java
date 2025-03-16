package com.bookhaven.auth_service.controller;

import com.bookhaven.auth_service.dto.request.LoginRequest;
import com.bookhaven.auth_service.dto.request.RegisterRequest;
import com.bookhaven.auth_service.dto.response.LoginResponse;
import com.bookhaven.auth_service.dto.response.RefreshTokenResponse;
import com.bookhaven.auth_service.dto.response.TokenResponse;
import com.bookhaven.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Successfully registered");
    }

    @PostMapping("refresh")
    public ResponseEntity<RefreshTokenResponse> refresh(@RequestParam String refreshToken) {
        return ResponseEntity.ok(authService.refresh(refreshToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return null;
    }
}
