package com.kunsas.grabgoods.reviewservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "reviews")
@Getter
@Setter
public class ReviewConfigInfoDto {
    private String message;
}