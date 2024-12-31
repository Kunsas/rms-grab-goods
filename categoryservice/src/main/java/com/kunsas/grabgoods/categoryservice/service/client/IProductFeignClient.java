package com.kunsas.grabgoods.categoryservice.service.client;

import com.kunsas.grabgoods.categoryservice.dto.CategoryRequestDto;
import com.kunsas.grabgoods.categoryservice.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "productservice")
public interface IProductFeignClient {

    @PutMapping(value = "/api/products/category/{id}", consumes = "application/json")
    public ResponseEntity<ResponseDto> updateCategoryInProduct(@PathVariable String id, @RequestBody CategoryRequestDto categoryRequestDto);

    @DeleteMapping("/api/products/category/{id}")
    public ResponseEntity<ResponseDto> deleteCategoryInProduct(@PathVariable String id);

}
