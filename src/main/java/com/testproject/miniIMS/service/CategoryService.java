package com.testproject.miniIMS.service;

import com.testproject.miniIMS.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryDto dto);
    List<CategoryDto> getAll();
    Page<CategoryDto> getAllPaged(Pageable pageable);
    CategoryDto getById(Long id);
    CategoryDto update(Long id, CategoryDto dto);
    void delete(Long id);
}
