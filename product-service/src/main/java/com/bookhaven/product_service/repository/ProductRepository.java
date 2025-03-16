package com.bookhaven.product_service.repository;

import com.bookhaven.product_service.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByIdInOrderByIdAsc(List<Integer> ids);

//    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllOrderByPriceDesc(Pageable pageable);

    Page<Product> findAllOrderByPriceAsc(Pageable pageable);

    Page<Product> findAllOrderByPublishDateDesc(Pageable pageable);
}
