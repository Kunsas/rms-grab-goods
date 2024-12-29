package com.kunsas.grabgoods.productservice.service.client;

import com.kunsas.grabgoods.productservice.dto.client.CategoryLookupRequestDto;
import com.kunsas.grabgoods.productservice.dto.client.CategoryResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("categoryservice")
public interface ICategoryFeignClient {

    @PostMapping(value = "/api/categories/lookup", consumes = "application/json")
    public List<CategoryResponseDto> getCategoriesByNames(@RequestBody CategoryLookupRequestDto categoryLookupRequestDto);
}
