package com.bookhaven.cart_service.repository.http_client;

import com.bookhaven.cart_service.dto.response.ProductResponse;
import com.bookhaven.cart_service.excepton.ClientException;
import org.springframework.cglib.core.internal.Function;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ProductClient {

    private final WebClient webClient;

    ProductClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8070/api/v1/products").build();
    }

    public Mono<ProductResponse> getProuctById(Integer id) {
        return this.webClient.get()
                .uri("/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse ->
                    Mono.error(new ClientException("Không thể lấy dữ liệu sản phẩm có ID::%d".formatted(id)))
                )
                .bodyToMono(ProductResponse.class)
                ;
    }
}
