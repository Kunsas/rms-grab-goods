package com.kunsas.grabgoods.userservice.constant;

public class UserConstants {

    private UserConstants(){}

    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "User created successfully!";
    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request processed successfully!";
    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 = "An error occurred. Please try again or contact Dev Team.";

    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "User does not exists.";
    public static final String USER_ALREADY_EXISTS_EXCEPTION_MESSAGE = "User with given email is already registered.";

    public static final String NOT_EMPTY_MESSAGE = " cannot be empty.";
    public static final String NOT_NULL_MESSAGE = " cannot be null.";
    public static final String INVALID_EMAIL_MESSAGE = "Please enter a valid email.";
    public static final String INVALID_PASSWORD_MESSAGE = "Password must be at least 8 characters long, include at least - one uppercase letter, one lowercase letter, one number, and one special character (e.g., @$!%*?&).";
    public static final String INVALID_USERNAME_MESSAGE = "Username must have length of min 8 and max 32 characters.";
    public static final String INVALID_MOBILE_NUMBER_MESSAGE = "Please enter a valid mobile number.";
    public static final String INVALID_USER_ID_MESSAGE = "Please provide valid User ID.";


}
