package com.bookhaven.cart_service.dto.response;

import java.math.BigDecimal;

public record ProductResponse(
    Integer id,
    String name,
    Integer quantity,
    BigDecimal price,
    Integer discount,
    String image
) {
}
