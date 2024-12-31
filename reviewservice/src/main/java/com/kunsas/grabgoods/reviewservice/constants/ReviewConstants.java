package com.kunsas.grabgoods.reviewservice.constants;

public class ReviewConstants {

    private ReviewConstants(){}

    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Review created successfully!";
    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request processed successfully!";
    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 = "An error occurred. Please try again or contact Dev Team.";

    public static final String REVIEW_NOT_FOUND_EXCEPTION_MESSAGE = "Review does not exists.";
    public static final String REVIEW_ALREADY_EXISTS_EXCEPTION_MESSAGE = "Review with given name already exists.";
    public static final String INVALID_REVIEW_TITLE_MESSAGE = "Review title must have alphanumeric characters of length min 1 and max 100, no special characters allowed except space";
    public static final String INVALID_REVIEW_DESCRIPTION_MESSAGE = "Invalid Review description. Review description length must be between 1 and 200, characters allowed are - numbers. alphabets, space, special characters(,_-|).";
    public static final String INVALID_REVIEW_ID_MESSAGE = "Please provide valid Review ID.";
    public static final String INVALID_REVIEW_RATING_MESSAGE = "Invalid Review rating. Review rating must be greater than or equal to zero. Allowed up to 1 decimal place.";


    public static final String REVIEW_ID_REGEX = "^[a-fA-F0-9]{24}$";
    public static final String REVIEW_USER_ID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";
    public static final String REVIEW_PRODUCT_ID_REGEX = "^[a-fA-F0-9]{24}$";
    public static final String REVIEW_TITLE_REGEX = "^[a-zA-Z0-9 ]+$";
    public static final String REVIEW_DESCRIPTION_REGEX = "[a-zA-Z0-9 _\\-,|]+{1,200}";
    public static final String REVIEW_RATING_REGEX = "^\\d{1}(\\.\\d{0,1})?$";

    public static final String NOT_EMPTY_MESSAGE = " cannot be empty.";
    public static final String NOT_NULL_MESSAGE = " cannot be null.";

}
