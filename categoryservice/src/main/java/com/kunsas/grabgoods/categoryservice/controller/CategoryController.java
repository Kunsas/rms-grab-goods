package com.kunsas.grabgoods.categoryservice.controller;

import com.kunsas.grabgoods.categoryservice.constant.CategoryConstants;
import com.kunsas.grabgoods.categoryservice.dto.*;
import com.kunsas.grabgoods.categoryservice.mapper.CategoryMapper;
import com.kunsas.grabgoods.categoryservice.service.ICategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/categories/", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private CategoryConfigInfoDto categoryConfigInfoDto;

    @PostMapping
    public ResponseEntity<ResponseDto> createCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto){
        categoryService.createCategory(categoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CategoryConstants.STATUS_201, CategoryConstants.MESSAGE_201));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@Pattern(regexp = CategoryConstants.CATEGORY_ID_REGEX, message = CategoryConstants.INVALID_CATEGORY_ID_MESSAGE) @PathVariable String id){
        CategoryResponseDto categoryResponseDto = categoryService.getCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponseDto);
    }

    @PostMapping("/lookup")
    public ResponseEntity<List<CategoryResponseDto>> getCategoriesByNames(@Valid @RequestBody CategoryLookupRequestDto categoryLookupRequestDto){
        List<CategoryResponseDto> categoryResponseDtoList = categoryService.getCategoriesByName(categoryLookupRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponseDtoList);
    }
    
    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(){
        List<CategoryResponseDto> categoryResponseDtoList = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponseDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateCategory(@Pattern(regexp = CategoryConstants.CATEGORY_ID_REGEX, message = CategoryConstants.INVALID_CATEGORY_ID_MESSAGE) @PathVariable String id, @Valid @RequestBody CategoryRequestDto categoryRequestDto){
        boolean isUpdated = categoryService.updateCategory(id, categoryRequestDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CategoryConstants.STATUS_200, CategoryConstants.MESSAGE_200));
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(CategoryConstants.STATUS_500, CategoryConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteCategory(@Pattern(regexp = CategoryConstants.CATEGORY_ID_REGEX, message = CategoryConstants.INVALID_CATEGORY_ID_MESSAGE) @PathVariable String id){
        boolean isDeleted = categoryService.deleteCategory(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CategoryConstants.STATUS_200, CategoryConstants.MESSAGE_200));
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(CategoryConstants.STATUS_500, CategoryConstants.MESSAGE_500));
        }
    }

    @GetMapping("/config-info")
    public ResponseEntity<CategoryConfigInfoDto> getConfigInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryConfigInfoDto);
    }

}
