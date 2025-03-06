package com.efashion.product_service.mapper;

import com.efashion.product_service.dto.request.ProductRequest;
import com.efashion.product_service.dto.response.ProductResponse;
import com.efashion.product_service.entity.Category;
import com.efashion.product_service.entity.Product;
import org.springframework.stereotype.Service;

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
                .build();
    }

    public ProductResponse toProductResponse(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }
}
