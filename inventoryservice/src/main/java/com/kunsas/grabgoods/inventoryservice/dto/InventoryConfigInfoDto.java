package com.kunsas.grabgoods.inventoryservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "inventory")
@Getter
@Setter
public class InventoryConfigInfoDto {
    private String message;
}
