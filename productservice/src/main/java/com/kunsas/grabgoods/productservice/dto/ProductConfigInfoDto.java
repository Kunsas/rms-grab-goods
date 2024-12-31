package com.kunsas.grabgoods.productservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "products")
@Getter
@Setter
public class ProductConfigInfoDto {
    private String message;
}
