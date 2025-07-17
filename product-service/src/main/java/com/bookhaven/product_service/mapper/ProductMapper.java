package com.bookhaven.product_service.mapper;

import com.bookhaven.product_service.dto.request.ProductRequest;
import com.bookhaven.product_service.dto.response.CategoryResponse;
import com.bookhaven.product_service.dto.response.ProductResponse;
import com.bookhaven.product_service.entity.Category;
import com.bookhaven.product_service.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest request){
        return Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .category(Category.builder()
                        .id(request.getCategoryId())
                        .build())
                .quantity(request.getQuantity())
                .language(request.getLanguage())
                .publishDate(request.getPublishDate())
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .image(null)
                .evaluate(request.getEvaluate())
                .discount(request.getDiscount())
                .build();
    }

    public ProductResponse toProductResponse(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                BigDecimal.valueOf(product.getPrice().doubleValue() * ((double) (100 - product.getDiscount()) / 100)),
                product.getQuantity(),
                product.getLanguage(),
                product.getPublishDate(),
                product.getAuthor(),
                product.getPublisher(),
                Arrays.asList(product.getImage().split("\\|")),
                new CategoryResponse(product.getCategory().getId(), product.getCategory().getName(), product.getCategory().getDescription()),
                product.getEvaluate(),
                product.getDiscount(),
                new ArrayList<>()
        );
    }
}
