package com.bookhaven.order_service.repository.web_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "cart-service",
        url = "${application.config.cart-url}"
)
public interface CartClient {

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteItemsInCart(@PathVariable("id") String customerId);
}
