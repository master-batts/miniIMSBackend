package com.testproject.miniIMS.service;

import com.testproject.miniIMS.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {
    ProductDto create(ProductDto dto);
    List<ProductDto> getAll();
    Page<ProductDto> getAll(Pageable pageable);
    ProductDto getById(Long id);
    ProductDto update(Long id, ProductDto dto);
    void delete(Long id);
}
