package com.kunsas.grabgoods.categoryservice.service;

import com.kunsas.grabgoods.categoryservice.dto.CategoryLookupRequestDto;
import com.kunsas.grabgoods.categoryservice.dto.CategoryRequestDto;
import com.kunsas.grabgoods.categoryservice.dto.CategoryResponseDto;

import java.util.List;

public interface ICategoryService {
    void createCategory(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto getCategoryById(String id);
    List<CategoryResponseDto> getCategoriesByName(CategoryLookupRequestDto names);
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto updateCategory(String id, CategoryRequestDto categoryRequestDto);
    String deleteCategory(String id);
}
