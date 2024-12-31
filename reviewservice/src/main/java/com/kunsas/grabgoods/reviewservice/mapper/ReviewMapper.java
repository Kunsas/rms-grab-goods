package com.kunsas.grabgoods.reviewservice.mapper;

import com.kunsas.grabgoods.reviewservice.dto.ReviewRequestDto;
import com.kunsas.grabgoods.reviewservice.dto.ReviewResponseDto;
import com.kunsas.grabgoods.reviewservice.entity.Review;

public class ReviewMapper {

    public static ReviewResponseDto mapToReviewResponseDto(Review review){
        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
        reviewResponseDto.setId(review.getId());
        reviewResponseDto.setTitle(review.getTitle());
        reviewResponseDto.setDescription(review.getDescription());
        reviewResponseDto.setRating(String.valueOf(review.getRating()));
        reviewResponseDto.setImageUrl(review.getImageUrl());
        reviewResponseDto.setUserId(review.getUserId());
        reviewResponseDto.setProductId(review.getProductId());

        return reviewResponseDto;
    }

    public static Review mapToReview(ReviewRequestDto reviewRequestDto, Review review){
        review.setTitle(reviewRequestDto.getTitle());
        review.setDescription(reviewRequestDto.getDescription());
        review.setRating(Float.valueOf(reviewRequestDto.getRating()));
        review.setImageUrl(reviewRequestDto.getImageUrl());
        review.setProductId(reviewRequestDto.getProductId());
        review.setUserId(reviewRequestDto.getUserId());

        return review;
    }

    public static Review mapToNewReview(ReviewRequestDto reviewRequestDto){
        Review review = new Review();
        review.setTitle(reviewRequestDto.getTitle());
        review.setDescription(reviewRequestDto.getDescription());
        review.setRating(Float.valueOf(reviewRequestDto.getRating()));
        review.setImageUrl(reviewRequestDto.getImageUrl());
        review.setProductId(reviewRequestDto.getProductId());
        review.setUserId(reviewRequestDto.getUserId());

        return review;
    }

}
