package com.bookhaven.auth_service.service;

import com.bookhaven.auth_service.dto.request.*;
import com.bookhaven.auth_service.dto.response.LoginResponse;
import com.bookhaven.auth_service.dto.response.RefreshTokenResponse;
import com.bookhaven.auth_service.http_client.KeyCloakClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final KeyCloakClient keyCloakClient;
    private final KeyClaokService keyClaokService;

    @Value("${application.keycloak.login-grant-type}")
    private String loginGrantType;
    @Value("${application.keycloak.refresh-grant-type}")
    private String refreshGrantType;
    @Value("${application.keycloak.client-id}")
    private String clientId;

    public LoginResponse login(LoginRequest request){
        var token = keyCloakClient.getToken(TokenRequest.builder()
                        .grant_type(loginGrantType)
                        .client_id(clientId)
                        .username(request.username())
                        .password(request.password())
                .build()
        );

        return new LoginResponse(
                token.access_token(),
                token.refresh_token(),
                token.expires_in()
        );
    }

    public void register(RegisterRequest request){
        FormRegisterUser form = FormRegisterUser.builder()
                .username(request.username())
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .credentals(Credentals.builder()
                        .type("password")
                        .temporary(false)
                        .value(request.password())
                        .build())
                .enable(true)
                .realmRoles(List.of("user"))
                .build();

        var token = keyClaokService.getManagerUserRoleToken();
        keyCloakClient.createUsers(token, form);
    }

    public RefreshTokenResponse refresh(String refreshToken){
        var token = keyCloakClient.refreshToken(ReFreshTokenRequest.builder()
                        .grant_type(refreshGrantType)
                        .client_id(clientId)
                        .refresh_token(refreshToken)
                .build());
        return new RefreshTokenResponse(
                token.access_token(),
                token.refresh_token(),
                token.expires_in()
        );
    }
}
