package com.kunsas.grabgoods.categoryservice.constant;

public class CategoryConstants {

    private CategoryConstants(){}

    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Category created successfully!";
    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request processed successfully!";
    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 = "An error occurred. Please try again or contact Dev Team.";

    public static final String CATEGORY_NOT_FOUND_EXCEPTION_MESSAGE = "Category does not exists.";
    public static final String CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "Category with given name already exists.";

    public static final String NOT_EMPTY_MESSAGE = " cannot be empty.";
    public static final String NOT_NULL_MESSAGE = " cannot be null.";


}
