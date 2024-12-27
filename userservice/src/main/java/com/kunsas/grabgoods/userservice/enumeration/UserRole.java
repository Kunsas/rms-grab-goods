package com.kunsas.grabgoods.userservice.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {
    CUSTOMER("customer"),
    STORE_MANAGER("store manager"),
    MARKETING_MANAGER("marketing manager");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    @JsonCreator
    public static UserRole fromValue(String value) {
        for (UserRole role : values()) {
            if (role.value.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + value);
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}
