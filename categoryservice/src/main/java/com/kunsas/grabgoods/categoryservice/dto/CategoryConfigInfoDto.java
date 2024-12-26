package com.kunsas.grabgoods.categoryservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "categories")
@Getter
@Setter
public class CategoryConfigInfoDto {
    private String message;
}