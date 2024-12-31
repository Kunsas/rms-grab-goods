package com.kunsas.grabgoods.inventoryservice.constant;

public class InventoryConstants {

    private InventoryConstants(){}

    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Product added to Inventory successfully!";
    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request processed successfully!";
    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 = "An error occurred. Please try again or contact Dev Team.";

    public static final String INVENTORY_NOT_FOUND_EXCEPTION_MESSAGE = "Inventory does not exists.";
    public static final String INVENTORY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "Inventory already exists.";

    public static final String INVALID_SKU_REGEX_MESSAGE = "Invalid SKU format. Please ensure the SKU contains alphanumeric characters separated by hyphens, with at least one hyphen and no trailing hyphens.";
    public static final String INVALID_STOCK_REGEX_MESSAGE = "Invalid Stock entered. Stock can range only from 0 to 100000";
    public static final String OUT_OF_STOCK_MESSAGE = "Insufficient stock to deduct the specified variation";

    public static final String SKU_REGEX = "^[A-Z0-9]+(-[A-Z0-9]+)+$";
    public static final String STOCK_REGEX = "^([1-9]?\\d{1,4}|100000)$";

    public static final String NOT_EMPTY_MESSAGE = " cannot be empty.";
    public static final String NOT_NULL_MESSAGE = " cannot be null.";

}
