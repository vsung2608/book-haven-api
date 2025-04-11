package com.bookhaven.product_service.repository;

import com.bookhaven.product_service.dto.response.ProductLimitedFieldsResponse;
import com.bookhaven.product_service.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByIdInOrderByIdAsc(List<Integer> ids);

    @Query("""
        select new com.bookhaven.product_service.dto.response.ProductLimitedFieldsResponse(p.id, p.name, p.discount, p.evaluate, p.image, p.price)
        from Product p
    """)
    Page<ProductLimitedFieldsResponse> findAllProducts(Pageable pageable);

    @Query("""
        select new com.bookhaven.product_service.dto.response.ProductLimitedFieldsResponse(p.id, p.name, p.discount, p.evaluate, p.image, p.price)
        from Product p
        order by p.price desc
    """)
    Page<ProductLimitedFieldsResponse> findAllByOrderByPriceDesc(Pageable pageable);

    @Query("""
        select new com.bookhaven.product_service.dto.response.ProductLimitedFieldsResponse(p.id, p.name, p.discount, p.evaluate, p.image, p.price)
        from Product p
        order by p.price asc
    """)
    Page<ProductLimitedFieldsResponse> findAllByOrderByPriceAsc(Pageable pageable);

    @Query("""
        select new com.bookhaven.product_service.dto.response.ProductLimitedFieldsResponse(p.id, p.name, p.discount, p.evaluate, p.image, p.price)
        from Product p
        order by p.publishDate desc
    """)
    Page<ProductLimitedFieldsResponse> findAllByOrderByPublishDateDesc(Pageable pageable);
}
