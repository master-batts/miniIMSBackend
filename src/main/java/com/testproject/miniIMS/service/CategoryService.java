package com.testproject.miniIMS.service;

import com.testproject.miniIMS.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryDto dto);
    List<CategoryDto> getAll();
    CategoryDto getById(Long id);
}
