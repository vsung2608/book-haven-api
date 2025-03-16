package com.bookhaven.auth_service.http_client;

import com.bookhaven.auth_service.configuration.FormFeignEncoderConfig;
import com.bookhaven.auth_service.dto.request.FormRegisterUser;
import com.bookhaven.auth_service.dto.request.ReFreshTokenRequest;
import com.bookhaven.auth_service.dto.request.TokenRequest;
import com.bookhaven.auth_service.dto.response.TokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(
        name = "keycloak-client",
        url = "${application.keycloak.domain}",
        configuration = FormFeignEncoderConfig.class
)
public interface KeyCloakClient {
    @PostMapping(value = "${application.keycloak.token-path}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    TokenResponse getToken(@RequestBody TokenRequest request);

    @PostMapping(value = "${application.keycloak.token-path}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    TokenResponse getManagerUserToken(Map<String, ?> data);

    @PostMapping(value = "${application.keycloak.user-path}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void createUsers(@RequestHeader("Authorization") String token, @RequestBody FormRegisterUser request);

    @PostMapping(value = "${application.keycloak.token-path}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    TokenResponse refreshToken(@RequestBody ReFreshTokenRequest request);
}
