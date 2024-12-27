package com.kunsas.grabgoods.categoryservice.mapper;

import com.kunsas.grabgoods.categoryservice.dto.CategoryRequestDto;
import com.kunsas.grabgoods.categoryservice.dto.CategoryResponseDto;
import com.kunsas.grabgoods.categoryservice.entity.Category;

public class CategoryMapper {

    public static CategoryResponseDto mapToCategoryResponseDto(Category category){
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        return categoryResponseDto;
    }

    public static Category mapToCategory(CategoryRequestDto categoryRequestDto, Category category){
        category.setName(categoryRequestDto.getName());
        return category;
    }

    public static Category mapToNewCategory(CategoryRequestDto categoryRequestDto){
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        return category;
    }

}
