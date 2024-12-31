package com.kunsas.grabgoods.reviewservice.service;

import com.kunsas.grabgoods.reviewservice.dto.ReviewRequestDto;
import com.kunsas.grabgoods.reviewservice.dto.ReviewResponseDto;

import java.util.List;

public interface IReviewService {
    void createReview(ReviewRequestDto reviewRequestDto);
    ReviewResponseDto getReviewById(String id);
    List<ReviewResponseDto> getAllReviews();
    List<ReviewResponseDto> getAllReviewsByProductId(String id);
    boolean deleteReviewsByProductId(String id);
    ReviewResponseDto updateReview(String id, ReviewRequestDto reviewRequestDto);
    String deleteReview(String id);
}
