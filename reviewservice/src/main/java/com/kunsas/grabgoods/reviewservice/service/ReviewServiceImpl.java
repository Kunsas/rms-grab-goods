package com.kunsas.grabgoods.reviewservice.service;

import com.kunsas.grabgoods.reviewservice.dto.ReviewRequestDto;
import com.kunsas.grabgoods.reviewservice.dto.ReviewResponseDto;
import com.kunsas.grabgoods.reviewservice.constants.ReviewConstants;
import com.kunsas.grabgoods.reviewservice.entity.Review;
import com.kunsas.grabgoods.reviewservice.exception.ReviewAlreadyExistsException;
import com.kunsas.grabgoods.reviewservice.exception.ReviewNotFoundException;
import com.kunsas.grabgoods.reviewservice.mapper.ReviewMapper;
import com.kunsas.grabgoods.reviewservice.repository.ReviewRepository;
import com.kunsas.grabgoods.reviewservice.service.client.IProductFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements IReviewService{
    
    private ReviewRepository reviewRepository;

    private IProductFeignClient productFeignClient;

    @Override
    public void createReview(ReviewRequestDto reviewRequestDto) {
        Optional<Review> existingReview = reviewRepository.findByUserIdAndProductIdAndTitle(reviewRequestDto.getUserId(), reviewRequestDto.getProductId(),reviewRequestDto.getTitle());
        if(existingReview.isPresent()) {
            throw new ReviewAlreadyExistsException(ReviewConstants.REVIEW_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        } else {
            Review newReview = ReviewMapper.mapToNewReview(reviewRequestDto);
            Review savedReview = reviewRepository.save(newReview);
            ReviewResponseDto reviewResponseDto = ReviewMapper.mapToReviewResponseDto(savedReview);
            productFeignClient.updateReviewInProduct(savedReview.getProductId(), savedReview.getId(), reviewResponseDto);
        }
    }

    @Override
    public ReviewResponseDto getReviewById(String id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(ReviewConstants.REVIEW_NOT_FOUND_EXCEPTION_MESSAGE));
        return ReviewMapper.mapToReviewResponseDto(review);
    }

    @Override
    public List<ReviewResponseDto> getAllReviews() {
        List<Review> reviewsWithProductId = reviewRepository.findAll();
        return reviewsWithProductId.stream().map(ReviewMapper::mapToReviewResponseDto).toList();
    }

    @Override
    public List<ReviewResponseDto> getAllReviewsByProductId(String id) {
        List<Review> reviewsWithProductId = reviewRepository.findByProductId(id).orElseThrow(() -> new ReviewNotFoundException(ReviewConstants.REVIEW_NOT_FOUND_EXCEPTION_MESSAGE));
        return reviewsWithProductId.stream().map(ReviewMapper::mapToReviewResponseDto).toList();
    }

    @Override
    public boolean deleteReviewsByProductId(String id) {
        List<Review> reviewsWithProductId = reviewRepository.findByProductId(id).orElseThrow(() -> new ReviewNotFoundException(ReviewConstants.REVIEW_NOT_FOUND_EXCEPTION_MESSAGE));
        if(!reviewsWithProductId.isEmpty()) {
            return reviewRepository.deleteByProductId(id) > 0;
        }
        return false;
    }

    @Override
    public ReviewResponseDto updateReview(String id, ReviewRequestDto reviewRequestDto) {
        Review existingReview = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(ReviewConstants.REVIEW_NOT_FOUND_EXCEPTION_MESSAGE));
        Review reviewToUpdate = ReviewMapper.mapToReview(reviewRequestDto, existingReview);
        Review updatedReview = reviewRepository.save(reviewToUpdate);
        ReviewResponseDto reviewResponseDto = ReviewMapper.mapToReviewResponseDto(updatedReview);
        productFeignClient.updateReviewInProduct(updatedReview.getProductId(), id, reviewResponseDto);
        return ReviewMapper.mapToReviewResponseDto(updatedReview);
    }

    @Override
    public String deleteReview(String id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(ReviewConstants.REVIEW_NOT_FOUND_EXCEPTION_MESSAGE));
        productFeignClient.deleteReviewInProduct(review.getProductId(), id);
        reviewRepository.deleteById(review.getId());
        return review.getId();
    }
}
