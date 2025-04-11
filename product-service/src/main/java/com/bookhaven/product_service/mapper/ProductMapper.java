package com.bookhaven.product_service.mapper;

import com.bookhaven.product_service.dto.request.ProductRequest;
import com.bookhaven.product_service.dto.response.CategoryResponse;
import com.bookhaven.product_service.dto.response.ProductResponse;
import com.bookhaven.product_service.entity.Category;
import com.bookhaven.product_service.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest request){
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .description(request.description())
                .category(Category.builder()
                        .id(request.categoryId())
                        .build())
                .quantity(request.qunaity())
                .language(request.language())
                .publishDate(request.publishDate())
                .author(request.author())
                .publisher(request.publisher())
                .image(request.image())
                .evaluate(request.evaluate())
                .discount(request.discount())
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
                product.getImage(),
                new CategoryResponse(product.getCategory().getId(), product.getCategory().getName(), product.getCategory().getDescription()),
                product.getEvaluate(),
                product.getDiscount()
        );
    }
}
