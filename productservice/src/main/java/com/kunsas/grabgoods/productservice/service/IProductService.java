package com.kunsas.grabgoods.productservice.service;

import com.kunsas.grabgoods.productservice.dto.ProductRequestDto;
import com.kunsas.grabgoods.productservice.dto.ProductResponseDto;
import com.kunsas.grabgoods.productservice.dto.ResponseDto;
import com.kunsas.grabgoods.productservice.dto.client.CategoryRequestDto;
import com.kunsas.grabgoods.productservice.dto.client.ReviewRequestDto;
import com.kunsas.grabgoods.productservice.dto.client.ReviewResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
    boolean updateReviewInProduct(String reviewId, String productId, ReviewResponseDto reviewResponseDto);
    boolean deleteReviewInProduct(String reviewId, String productId);
}
