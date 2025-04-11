package com.bookhaven.auth_service.dto.response;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        Integer expiresIn
) {
}
