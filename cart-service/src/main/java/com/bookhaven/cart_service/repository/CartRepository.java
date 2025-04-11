package com.bookhaven.cart_service.repository;

import com.bookhaven.cart_service.entity.Cart;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CartRepository extends ReactiveMongoRepository<Cart, String> {

    Mono<Cart> findByCustomerId(String customerId);
}
