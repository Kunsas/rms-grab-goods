package com.kunsas.grabgoods.categoryservice.service;

import com.kunsas.grabgoods.categoryservice.constant.CategoryConstants;
import com.kunsas.grabgoods.categoryservice.dto.CategoryLookupRequestDto;
import com.kunsas.grabgoods.categoryservice.dto.CategoryRequestDto;
import com.kunsas.grabgoods.categoryservice.dto.CategoryResponseDto;
import com.kunsas.grabgoods.categoryservice.entity.Category;
import com.kunsas.grabgoods.categoryservice.exception.CategoryAlreadyExistsException;
import com.kunsas.grabgoods.categoryservice.exception.CategoryNotFoundException;
import com.kunsas.grabgoods.categoryservice.mapper.CategoryMapper;
import com.kunsas.grabgoods.categoryservice.repository.CategoryRepository;
import com.kunsas.grabgoods.categoryservice.service.client.IProductFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements ICategoryService{

    private CategoryRepository categoryRepository;

    private IProductFeignClient productFeignClient;

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
    public List<CategoryResponseDto> getCategoriesByName(CategoryLookupRequestDto categoryLookupRequestDto) {
        List<Category> categories = categoryRepository.findByNameIn(categoryLookupRequestDto.getNames()).orElseThrow(() -> new CategoryNotFoundException(CategoryConstants.CATEGORY_NOT_FOUND_EXCEPTION_MESSAGE));
        return categories.stream().map(CategoryMapper::mapToCategoryResponseDto).toList();
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(CategoryMapper::mapToCategoryResponseDto).toList();
    }

    @Override
    public CategoryResponseDto updateCategory(String id, CategoryRequestDto categoryRequestDto) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(CategoryConstants.CATEGORY_NOT_FOUND_EXCEPTION_MESSAGE));
        Category categoryToUpdate = CategoryMapper.mapToCategory(categoryRequestDto, existingCategory);
        Category updatedCategory = categoryRepository.save(categoryToUpdate);
        productFeignClient.updateCategoryInProduct(id, categoryRequestDto);
        return CategoryMapper.mapToCategoryResponseDto(updatedCategory);
    }

    @Override
    public String deleteCategory(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(CategoryConstants.CATEGORY_NOT_FOUND_EXCEPTION_MESSAGE));
        categoryRepository.deleteById(category.getId());
        productFeignClient.deleteCategoryInProduct(id);
        return category.getId();
    }
}
