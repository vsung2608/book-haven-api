package com.bookhaven.product_service.service;

import com.bookhaven.product_service.dto.response.CategoryResponse;
import com.bookhaven.product_service.mapper.CateoryMapper;
import com.bookhaven.product_service.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CateoryMapper mapper;
    private final CategoryRepository repository;

    public List<CategoryResponse> findAll(){
        return repository.findAll()
                .stream().map(mapper::categoryToCategoryResponse)
                .toList();
    }
}
