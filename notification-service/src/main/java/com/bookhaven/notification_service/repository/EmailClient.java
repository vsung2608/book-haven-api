package com.bookhaven.notification_service.repository;

import com.bookhaven.notification_service.dto.request.EmailRequest;
import com.bookhaven.notification_service.dto.response.EmailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "email-client", url = "https://api.brevo.com")
public interface EmailClient {

    @PostMapping(value = "/v3/smtp/email", produces = MediaType.APPLICATION_JSON_VALUE)
    EmailResponse send(@RequestHeader("api-key") String apiKey, @RequestBody EmailRequest body);

}
