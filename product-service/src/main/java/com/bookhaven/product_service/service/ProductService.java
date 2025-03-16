package com.bookhaven.product_service.service;

import com.bookhaven.common.dto.PurchaseRequest;
import com.bookhaven.common.dto.PurchaseResponse;
import com.bookhaven.product_service.dto.request.ProductRequest;
import com.bookhaven.product_service.dto.response.PageResponse;
import com.bookhaven.product_service.dto.response.ProductResponse;
import com.bookhaven.product_service.mapper.ProductMapper;
import com.bookhaven.product_service.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse addProduct(ProductRequest request) {
        var product = productMapper.toProduct(request);
        var response = productRepository.save(product);
        return productMapper.toProductResponse(response);
    }

    public ProductResponse updateProduct(int id, ProductRequest request) {
        productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        var product = productMapper.toProduct(request);
        var response = productRepository.save(product);
        return productMapper.toProductResponse(response);
    }

    public PageResponse getAllProducts(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var products = productRepository.findAll(pageable);
        return new PageResponse(
                page,
                products.getTotalPages(),
                products.getSize(),
                products.getTotalElements(),
                products.getContent().stream().map(productMapper::toProductResponse).toList()
        );
    }

    public ProductResponse findById(int id) {
        return productRepository.findById(id)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product bot found with the ID:: %d".formatted(id)));
    }

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> request) {
        List<Integer> ids = new ArrayList<>();
        for (PurchaseRequest product : request) {
            ids.add(product.id());
        }

        Map<Integer, Integer> map = request.stream()
                .collect(Collectors.toMap(
                        PurchaseRequest::id, PurchaseRequest::quantity
                ));

        return productRepository.findByIdInOrderByIdAsc(ids).stream()
                .map(product -> {
                    return new PurchaseResponse(
                            product.getId(),
                            product.getName(),
                            map.get(product.getId()),
                            product.getQuantity() > map.get(product.getId()),
                            product.getPrice()
                    );
                }).toList();
    }

    public PageResponse getLatestProduct(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var products = productRepository.findAllOrderByPublishDateDesc(pageable);
        return new PageResponse(
                page,
                products.getTotalPages(),
                products.getSize(),
                products.getTotalElements(),
                products.getContent().stream().map(productMapper::toProductResponse).toList()
        );
    }

    public PageResponse getBestSellerProducts(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var products = productRepository.findAllOrderByPublishDateDesc(pageable);
        return new PageResponse(
                page,
                products.getTotalPages(),
                products.getSize(),
                products.getTotalElements(),
                products.getContent().stream().map(productMapper::toProductResponse).toList()
        );
    }

    public PageResponse getProductsByPriceAsc(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var products = productRepository.findAllOrderByPriceAsc(pageable);
        return new PageResponse(
                page,
                products.getTotalPages(),
                products.getSize(),
                products.getTotalElements(),
                products.getContent().stream().map(productMapper::toProductResponse).toList()
        );
    }

    public PageResponse getProductsByPriceDesc(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var products = productRepository.findAllOrderByPriceDesc(pageable);
        return new PageResponse(
                page,
                products.getTotalPages(),
                products.getSize(),
                products.getTotalElements(),
                products.getContent().stream().map(productMapper::toProductResponse).toList()
        );
    }
}
