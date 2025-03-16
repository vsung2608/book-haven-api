package com.bookhaven.auth_service.service;

import com.bookhaven.auth_service.http_client.KeyCloakClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KeyClaokService {
    private final KeyCloakClient keyCloakClient;
    @Value("${application.keycloak.admin.grant-type}")
    private String grantType;
    @Value("${application.keycloak.admin.client-id}")
    private String clientId;
    @Value("${application.keycloak.admin.client-secret}")
    private String clientSecret;

    public String getManagerUserRoleToken(){
        Map<String, String> map = new HashMap<>();
        map.put("grant_type", grantType);
        map.put("client_id", clientId);
        map.put("client_secret", clientSecret);
        return "Bearer: %s".formatted(keyCloakClient.getManagerUserToken(map).access_token());
    }
}
