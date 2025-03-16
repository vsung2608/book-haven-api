package com.bookhaven.auth_service.dto.request;

public record RegisterRequest(
        String username,
        String firstName,
        String lastName,
        String email,
        String password
) {
}
