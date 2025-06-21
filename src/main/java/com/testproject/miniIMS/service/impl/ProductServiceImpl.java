package com.testproject.miniIMS.service.impl;


import com.testproject.miniIMS.dto.ProductDto;
import com.testproject.miniIMS.entity.Category;
import com.testproject.miniIMS.entity.Product;
import com.testproject.miniIMS.exception.ResourceNotFoundException;
import com.testproject.miniIMS.repository.CategoryRepository;
import com.testproject.miniIMS.repository.ProductRepository;
import com.testproject.miniIMS.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductDto create(ProductDto dto) {
        Product product = modelMapper.map(dto, Product.class);

        // Set category manually
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + dto.getCategoryId()));
        product.setCategory(category);

        Product saved = productRepository.save(product);
        ProductDto response = modelMapper.map(saved, ProductDto.class);
        response.setCategoryId(category.getId());
        response.setCategoryName(category.getName());
        return response;
    }

    @Override
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream()
                .map(product -> {
                    ProductDto dto = modelMapper.map(product, ProductDto.class);
                    dto.setCategoryId(product.getCategory().getId());
                    dto.setCategoryName(product.getCategory().getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        ProductDto dto = modelMapper.map(product, ProductDto.class);
        dto.setCategoryId(product.getCategory().getId());
        dto.setCategoryName(product.getCategory().getName());
        return dto;
    }

    @Override
    public ProductDto update(Long id, ProductDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + dto.getCategoryId()));

        modelMapper.map(dto, product);
        product.setCategory(category);

        Product updated = productRepository.save(product);
        ProductDto updatedDto = modelMapper.map(updated, ProductDto.class);
        updatedDto.setCategoryId(category.getId());
        updatedDto.setCategoryName(category.getName());
        return updatedDto;
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }
}