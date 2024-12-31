package com.kunsas.grabgoods.reviewservice.service.client;

import com.kunsas.grabgoods.reviewservice.dto.ResponseDto;
import com.kunsas.grabgoods.reviewservice.dto.ReviewRequestDto;
import com.kunsas.grabgoods.reviewservice.dto.ReviewResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "productservice")
public interface IProductFeignClient {

    @PutMapping(value = "/api/products/{productId}/reviews/{reviewId}", consumes = "application/json")
    public ResponseEntity<ResponseDto> updateReviewInProduct(@PathVariable String productId, @PathVariable String reviewId, @RequestBody ReviewResponseDto reviewResponseDto);

    @DeleteMapping(value = "/api/products/{productId}/reviews/{reviewId}")
    public ResponseEntity<ResponseDto> deleteReviewInProduct(@PathVariable String productId, @PathVariable String reviewId);

}
