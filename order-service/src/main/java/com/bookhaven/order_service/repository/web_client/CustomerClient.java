package com.bookhaven.order_service.repository.web_client;

import com.bookhaven.common.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}"
)
public interface CustomerClient {

    @GetMapping(value = "/{customer-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Optional<CustomerResponse> findCustomerById(@PathVariable("customer-id") String customerId);
}
