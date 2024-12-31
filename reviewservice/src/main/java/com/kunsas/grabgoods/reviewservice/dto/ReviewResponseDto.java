package com.kunsas.grabgoods.reviewservice.dto;

import com.kunsas.grabgoods.reviewservice.constants.ReviewConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewResponseDto {

    @NotEmpty(message = "Review ID" + ReviewConstants.NOT_EMPTY_MESSAGE)
    @Pattern(regexp = ReviewConstants.REVIEW_ID_REGEX, message = ReviewConstants.INVALID_REVIEW_ID_MESSAGE)
    private String id;

    @NotEmpty(message = "User ID" + ReviewConstants.NOT_EMPTY_MESSAGE)
    @Pattern(regexp = ReviewConstants.REVIEW_USER_ID_REGEX, message = ReviewConstants.INVALID_REVIEW_ID_MESSAGE)
    private String userId;

    @NotEmpty(message = "Review Name" + ReviewConstants.NOT_EMPTY_MESSAGE)
    @Size(min=1, max = 100, message = ReviewConstants.INVALID_REVIEW_TITLE_MESSAGE)
    @Pattern(regexp = ReviewConstants.REVIEW_TITLE_REGEX, message = ReviewConstants.INVALID_REVIEW_TITLE_MESSAGE)
    private String title;

    @NotEmpty(message = "Review Description" + ReviewConstants.NOT_EMPTY_MESSAGE)
    @Size(min=1, max = 200, message = ReviewConstants.INVALID_REVIEW_DESCRIPTION_MESSAGE)
    @Pattern(regexp = ReviewConstants.REVIEW_DESCRIPTION_REGEX, message = ReviewConstants.INVALID_REVIEW_DESCRIPTION_MESSAGE)
    private String description;

    @NotNull(message = "Review Price" + ReviewConstants.NOT_NULL_MESSAGE)
    @Pattern(regexp = ReviewConstants.REVIEW_RATING_REGEX, message = ReviewConstants.INVALID_REVIEW_RATING_MESSAGE)
    private String rating;
    
    private String imageUrl;

    @NotEmpty(message = "Review ID" + ReviewConstants.NOT_EMPTY_MESSAGE)
    @Pattern(regexp = ReviewConstants.REVIEW_PRODUCT_ID_REGEX, message = ReviewConstants.INVALID_REVIEW_ID_MESSAGE)
    private String productId;
}
