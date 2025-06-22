package com.testproject.miniIMS.service.impl;

import com.testproject.miniIMS.dto.CategoryDto;
import com.testproject.miniIMS.entity.Category;
import com.testproject.miniIMS.exception.ResourceNotFoundException;
import com.testproject.miniIMS.repository.CategoryRepository;
import com.testproject.miniIMS.service.CategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryDto create(CategoryDto dto) {
        Category category = modelMapper.map(dto, Category.class);
        Category saved = categoryRepository.save(category);
        return modelMapper.map(saved, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto update(Long id, CategoryDto dto) {
        if (id == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        Category updated = categoryRepository.save(category);
        return modelMapper.map(updated, CategoryDto.class);
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        if (category.getProducts() != null && !category.getProducts().isEmpty()) {
            throw new IllegalStateException("Cannot delete category with existing products");
        }

        categoryRepository.delete(category);
    }
}
