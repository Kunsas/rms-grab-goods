package com.kunsas.grabgoods.productservice.service.client;

import com.kunsas.grabgoods.productservice.dto.ResponseDto;
import com.kunsas.grabgoods.productservice.dto.client.ReviewResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("reviewservice")
public interface IReviewFeignClient {

    @GetMapping(value = "/api/reviews/product/{id}")
    public ResponseEntity<List<ReviewResponseDto>> getAllReviewsByProductId(@PathVariable String id);

    @DeleteMapping(value = "/api/reviews/product/{id}")
    public ResponseEntity<ResponseDto> deleteReviewsByProductId(@PathVariable String id);
}
