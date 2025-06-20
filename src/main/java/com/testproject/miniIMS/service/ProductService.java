package com.testproject.miniIMS.service;

import com.testproject.miniIMS.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductDto dto);
    List<ProductDto> getAll();
    ProductDto getById(Long id);
    ProductDto update(Long id, ProductDto dto);
    void delete(Long id);
}
