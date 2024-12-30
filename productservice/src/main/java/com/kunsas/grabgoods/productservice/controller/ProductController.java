package com.kunsas.grabgoods.productservice.controller;

import com.kunsas.grabgoods.productservice.constant.ProductConstants;
import com.kunsas.grabgoods.productservice.dto.ProductConfigInfoDto;
import com.kunsas.grabgoods.productservice.dto.ProductRequestDto;
import com.kunsas.grabgoods.productservice.dto.ProductResponseDto;
import com.kunsas.grabgoods.productservice.dto.ResponseDto;
import com.kunsas.grabgoods.productservice.dto.client.CategoryRequestDto;
import com.kunsas.grabgoods.productservice.service.IProductService;
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
@RequestMapping(path = "/api/products/", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ProductConfigInfoDto productConfigInfoDto;

    @PostMapping
    public ResponseEntity<ResponseDto> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto){
        productService.createProduct(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(ProductConstants.STATUS_201, ProductConstants.MESSAGE_201));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@Pattern(regexp = ProductConstants.PRODUCT_ID_REGEX, message = ProductConstants.INVALID_PRODUCT_ID_MESSAGE) @PathVariable String id){
        ProductResponseDto productResponseDto = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
    
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        List<ProductResponseDto> productResponseDtoList = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateProduct(@Pattern(regexp = ProductConstants.PRODUCT_ID_REGEX, message = ProductConstants.INVALID_PRODUCT_ID_MESSAGE) @PathVariable String id, @Valid @RequestBody ProductRequestDto productRequestDto){
        boolean isUpdated = productService.updateProduct(id, productRequestDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_200));
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(ProductConstants.STATUS_500, ProductConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteProduct(@Pattern(regexp = ProductConstants.PRODUCT_ID_REGEX, message = ProductConstants.INVALID_PRODUCT_ID_MESSAGE) @PathVariable String id){
        boolean isDeleted = productService.deleteProduct(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_200));
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(ProductConstants.STATUS_500, ProductConstants.MESSAGE_500));
        }
    }

    @GetMapping("/config-info")
    public ResponseEntity<ProductConfigInfoDto> getConfigInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(productConfigInfoDto);
    }

    @PutMapping("category/{id}")
    public ResponseEntity<ResponseDto> updateCategoryInProduct(@PathVariable String id, @RequestBody CategoryRequestDto categoryRequestDto){
        boolean isUpdated = productService.updateCategoryInProduct(id, categoryRequestDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_200));
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(ProductConstants.STATUS_500, ProductConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("category/{id}")
    public ResponseEntity<ResponseDto> deleteCategoryInProduct(@PathVariable String id){
        boolean isDeleted = productService.deleteCategoryInProduct(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_200));
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(ProductConstants.STATUS_500, ProductConstants.MESSAGE_500));
        }
    }

}
