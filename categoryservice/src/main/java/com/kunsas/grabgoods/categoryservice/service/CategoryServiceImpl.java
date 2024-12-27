package com.kunsas.grabgoods.categoryservice.service;

import com.kunsas.grabgoods.categoryservice.constant.CategoryConstants;
import com.kunsas.grabgoods.categoryservice.dto.CategoryRequestDto;
import com.kunsas.grabgoods.categoryservice.dto.CategoryResponseDto;
import com.kunsas.grabgoods.categoryservice.entity.Category;
import com.kunsas.grabgoods.categoryservice.exception.CategoryAlreadyExistsException;
import com.kunsas.grabgoods.categoryservice.exception.CategoryNotFoundException;
import com.kunsas.grabgoods.categoryservice.mapper.CategoryMapper;
import com.kunsas.grabgoods.categoryservice.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements ICategoryService{

    private CategoryRepository categoryRepository;

    @Override
    public void createCategory(CategoryRequestDto categoryRequestDto) {
        Optional<Category> existingCategory = categoryRepository.findByName(categoryRequestDto.getName());
        if(existingCategory.isPresent()) {
            throw new CategoryAlreadyExistsException(CategoryConstants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        } else {
            Category newCategory = CategoryMapper.mapToNewCategory(categoryRequestDto);
            categoryRepository.insert(newCategory);
        }
    }

    @Override
    public CategoryResponseDto getCategoryById(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(CategoryConstants.CATEGORY_NOT_FOUND_EXCEPTION_MESSAGE));
        return CategoryMapper.mapToCategoryResponseDto(category);
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(CategoryMapper::mapToCategoryResponseDto).toList();
    }

    @Override
    public boolean updateCategory(String id, CategoryRequestDto categoryRequestDto) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(CategoryConstants.CATEGORY_NOT_FOUND_EXCEPTION_MESSAGE));
        Category updatedCategory = CategoryMapper.mapToCategory(categoryRequestDto, existingCategory);
        categoryRepository.save(updatedCategory);
        return true;
    }

    @Override
    public boolean deleteCategory(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(CategoryConstants.CATEGORY_NOT_FOUND_EXCEPTION_MESSAGE));
        categoryRepository.deleteById(category.getId());
        return true;
    }
}
