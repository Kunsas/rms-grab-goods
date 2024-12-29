package com.kunsas.grabgoods.productservice.service;

import com.kunsas.grabgoods.productservice.dto.ProductRequestDto;
import com.kunsas.grabgoods.productservice.dto.ProductResponseDto;

import java.util.List;

public interface IProductService {

    void createProduct(ProductRequestDto productRequestDto);
    ProductResponseDto getProductById(String id);
    List<ProductResponseDto> getAllProducts();
    boolean updateProduct(String id, ProductRequestDto productRequestDto);
    boolean deleteProduct(String id);

}
