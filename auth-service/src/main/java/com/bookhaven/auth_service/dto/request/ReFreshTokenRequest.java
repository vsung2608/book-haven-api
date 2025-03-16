package com.bookhaven.auth_service.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReFreshTokenRequest {
    private String grant_type;
    private String client_id;
    private String refresh_token;
}
