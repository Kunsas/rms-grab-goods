package com.kunsas.grabgoods.productservice.service;

import com.kunsas.grabgoods.productservice.dto.ProductRequestDto;
import com.kunsas.grabgoods.productservice.dto.ProductResponseDto;
import com.kunsas.grabgoods.productservice.dto.ResponseDto;
import com.kunsas.grabgoods.productservice.dto.client.CategoryRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IProductService {

    void createProduct(ProductRequestDto productRequestDto);
    ProductResponseDto getProductById(String id);
    List<ProductResponseDto> getAllProducts();
    boolean updateProduct(String id, ProductRequestDto productRequestDto);
    boolean deleteProduct(String id);
    boolean updateCategoryInProduct(String id, CategoryRequestDto categoryRequestDto);
    boolean deleteCategoryInProduct(String id);
}
