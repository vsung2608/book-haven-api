package com.bookhaven.auth_service.dto.request;

public record LoginRequest(
        String username,
        String password
) {
}
