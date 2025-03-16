package com.bookhaven.auth_service.dto.response;

public record RefreshTokenResponse(
        String accessToken,
        String refreshToken
) {
}
