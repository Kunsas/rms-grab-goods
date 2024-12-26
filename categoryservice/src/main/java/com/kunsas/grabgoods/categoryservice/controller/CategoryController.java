package com.kunsas.grabgoods.categoryservice.controller;

import com.kunsas.grabgoods.categoryservice.dto.CategoryConfigInfoDto;
import com.kunsas.grabgoods.categoryservice.dto.CategoryRequestDto;
import com.kunsas.grabgoods.categoryservice.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/categories/")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private CategoryConfigInfoDto categoryConfigInfoDto;

    @PostMapping
    public void createCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        categoryService.createCategory(categoryRequestDto);
    }

    @GetMapping("/config-info")
    public ResponseEntity<CategoryConfigInfoDto> getConfigInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryConfigInfoDto);
    }

}
