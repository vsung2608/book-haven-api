package com.bookhaven.auth_service.dto.response;

public record TokenResponse(
        String access_token,
        String refresh_token,
        Integer expires_in,
        Integer refresh_expires_in,
        String token_type,
        String session_state,
        String scope
) {
}
