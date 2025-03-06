package com.efashion.order_service.repository.web_client;

import com.efashion.common.dto.PurchaseRequest;
import com.efashion.common.dto.PurchaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "product-service",
        url = "${application.config.product-url}"
)
public interface ProductClient {

    @PostMapping(
            value = "/purchase",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<PurchaseResponse> purchase(@RequestBody List<PurchaseRequest> requests);
}
