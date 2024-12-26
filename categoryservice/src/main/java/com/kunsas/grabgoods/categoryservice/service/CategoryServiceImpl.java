package com.kunsas.grabgoods.categoryservice.service;

import com.kunsas.grabgoods.categoryservice.dto.CategoryRequestDto;
import com.kunsas.grabgoods.categoryservice.dto.CategoryResponseDto;
import com.kunsas.grabgoods.categoryservice.entity.Category;
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
//            throw new CategoryAlreadyExistsException(CategoryConstants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE);
            System.out.println("Category Already Exists");
        } else {
            Category newCategory = new Category();
            newCategory.setName(categoryRequestDto.getName());
            categoryRepository.insert(newCategory);
        }
    }

    @Override
    public CategoryResponseDto getCategoryById(String id) {
        return null;
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return List.of();
    }

    @Override
    public boolean updateCategory(String id, CategoryRequestDto categoryRequestDto) {
        return false;
    }

    @Override
    public boolean deleteCategory(String id) {
        return false;
    }
}
