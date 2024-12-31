package com.kunsas.grabgoods.reviewservice.controller;

import com.kunsas.grabgoods.reviewservice.constants.ReviewConstants;
import com.kunsas.grabgoods.reviewservice.constants.client.ProductConstants;
import com.kunsas.grabgoods.reviewservice.dto.ResponseDto;
import com.kunsas.grabgoods.reviewservice.dto.ReviewConfigInfoDto;
import com.kunsas.grabgoods.reviewservice.dto.ReviewRequestDto;
import com.kunsas.grabgoods.reviewservice.dto.ReviewResponseDto;
import com.kunsas.grabgoods.reviewservice.service.IReviewService;
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
@RequestMapping(path = "/api/reviews/", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ReviewController {

    @Autowired
    private IReviewService reviewService;

    @Autowired
    private ReviewConfigInfoDto reviewConfigInfoDto;

    @PostMapping
    public ResponseEntity<ResponseDto> createReview(@Valid @RequestBody ReviewRequestDto reviewRequestDto){
        reviewService.createReview(reviewRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(ReviewConstants.STATUS_201, ReviewConstants.MESSAGE_201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> getReviewById(@Pattern(regexp = ReviewConstants.REVIEW_ID_REGEX, message = ReviewConstants.INVALID_REVIEW_ID_MESSAGE) @PathVariable String id){
        ReviewResponseDto reviewResponseDto = reviewService.getReviewById(id);
        return ResponseEntity.status(HttpStatus.OK).body(reviewResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews(){
        List<ReviewResponseDto> reviewResponseDto = reviewService.getAllReviews();
        return ResponseEntity.status(HttpStatus.OK).body(reviewResponseDto);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<ReviewResponseDto>> getAllReviewsByProductId(@Pattern(regexp = ProductConstants.PRODUCT_ID_REGEX, message = ProductConstants.INVALID_PRODUCT_ID_MESSAGE) @PathVariable String id){
        List<ReviewResponseDto> reviewResponseDto = reviewService.getAllReviewsByProductId(id);
        return ResponseEntity.status(HttpStatus.OK).body(reviewResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> updateReview(@Pattern(regexp = ReviewConstants.REVIEW_ID_REGEX, message = ReviewConstants.INVALID_REVIEW_ID_MESSAGE) @PathVariable String id, @Valid @RequestBody ReviewRequestDto reviewRequestDto){
        ReviewResponseDto updatedReviewResponseDto = reviewService.updateReview(id, reviewRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedReviewResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteReview(@Pattern(regexp = ReviewConstants.REVIEW_ID_REGEX, message = ReviewConstants.INVALID_REVIEW_ID_MESSAGE) @PathVariable String id){
        boolean isDeleted = id.equals(reviewService.deleteReview(id));
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(ReviewConstants.STATUS_200, ReviewConstants.MESSAGE_200));
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(ReviewConstants.STATUS_500, ReviewConstants.MESSAGE_500));
        }
    }

    @GetMapping("/config-info")
    public ResponseEntity<ReviewConfigInfoDto> getConfigInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(reviewConfigInfoDto);
    }

}
