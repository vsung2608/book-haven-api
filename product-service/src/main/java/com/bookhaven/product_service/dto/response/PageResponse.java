package com.bookhaven.product_service.dto.response;

import java.util.List;

public record PageResponse(
        int currentPage,
        int totalPages,
        int pageSize,
        long totalElements,
        List<ProductLimitedFieldsResponse> data
) {
}
