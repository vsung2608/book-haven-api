package com.efashion.product_service.repository;

import com.efashion.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByIdInOrderByIdAsc(List<Integer> ids);
}
