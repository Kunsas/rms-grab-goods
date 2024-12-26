package com.kunsas.grabgoods.userservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "users")
@Getter
@Setter
public class UserConfigInfoDto {
    private String message;
}
