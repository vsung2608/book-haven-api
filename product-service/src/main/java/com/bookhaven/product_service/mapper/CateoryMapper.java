package com.bookhaven.product_service.mapper;

import com.bookhaven.product_service.dto.response.CategoryResponse;
import com.bookhaven.product_service.entity.Category;
import org.springframework.stereotype.Service;

@Service
public class CateoryMapper {
    public CategoryResponse categoryToCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}
