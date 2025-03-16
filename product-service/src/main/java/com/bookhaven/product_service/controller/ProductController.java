package com.bookhaven.product_service.controller;

import com.bookhaven.common.dto.PurchaseRequest;
import com.bookhaven.common.dto.PurchaseResponse;
import com.bookhaven.product_service.dto.request.ProductRequest;
import com.bookhaven.product_service.dto.response.PageResponse;
import com.bookhaven.product_service.dto.response.ProductResponse;
import com.bookhaven.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.addProduct(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable int id, @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.updateProduct(id, productRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable int id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse> getProducts(
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "12") Integer size
    ) {
        if(sort != null && sort.equals("price_asc")){
            return ResponseEntity.ok(productService.getProductsByPriceAsc(page, size));
        }else if(sort != null && sort.equals("price_desc")){
            return ResponseEntity.ok(productService.getProductsByPriceDesc(page, size));
        }
        return ResponseEntity.ok(productService.getAllProducts(page, size));
    }

    @GetMapping("/latest")
    public ResponseEntity<PageResponse> getLatestProducts(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "12") Integer size
    ) {
        return ResponseEntity.ok(productService.getLatestProduct(page, size));
    }

    @GetMapping("/best-seller")
    public ResponseEntity<PageResponse> getBestSellerProducts(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "12") Integer size
    ) {
        return ResponseEntity.ok(productService.getBestSellerProducts(page, size));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<PurchaseResponse>> purchaseProducts(@RequestBody List<PurchaseRequest> requests) {
        return ResponseEntity.ok(productService.purchaseProducts(requests));
    }
}
