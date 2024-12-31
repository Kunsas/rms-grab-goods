package com.kunsas.grabgoods.productservice.constant;

public class ProductConstants {

    private ProductConstants(){}

    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Product created successfully!";
    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request processed successfully!";
    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 = "An error occurred. Please try again or contact Dev Team.";

    public static final String PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE = "Product does not exists.";
    public static final String PRODUCT_ALREADY_EXISTS_EXCEPTION_MESSAGE = "Product with given name already exists.";
    public static final String INVALID_PRODUCT_NAME_MESSAGE = "Invalid Product name. Product name length must be between 1 and 100, characters allowed are - numbers. alphabets, space, special characters(,_-|).";
    public static final String INVALID_PRODUCT_DESCRIPTION_MESSAGE = "Invalid Product description. Product description length must be between 1 and 200, characters allowed are - numbers. alphabets, space, special characters(,_-|).";
    public static final String INVALID_PRODUCT_ID_MESSAGE = "Please provide valid Product ID.";
    public static final String INVALID_PRODUCT_PRICE_MESSAGE = "Invalid Product price.Product Price must be greater than or equal to zero. Allowed up to 2 decimal places.";
    public static final String PRODUCT_OUT_OF_STOCK = "Product out of Stock";

    public static final String PRODUCT_ID_REGEX = "^[a-fA-F0-9]{24}$";
    public static final String PRODUCT_NAME_REGEX = "[a-zA-Z0-9 _\\-,|]+{1,100}";
    public static final String PRODUCT_DESCRIPTION_REGEX = "[a-zA-Z0-9 _\\-,|]+{1,200}";
    public static final String PRODUCT_PRICE_REGEX = "^\\d{1,10}(\\.\\d{1,2})?$";

    public static final String NOT_EMPTY_MESSAGE = " cannot be empty.";
    public static final String NOT_NULL_MESSAGE = " cannot be null.";

}
