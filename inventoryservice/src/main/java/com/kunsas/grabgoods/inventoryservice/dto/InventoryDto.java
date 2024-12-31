package com.kunsas.grabgoods.inventoryservice.dto;

import com.kunsas.grabgoods.inventoryservice.constant.InventoryConstants;
import com.kunsas.grabgoods.inventoryservice.constant.client.ProductConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class InventoryDto {

    @NotEmpty(message = "SKU " + InventoryConstants.NOT_EMPTY_MESSAGE)
    @Pattern(regexp = InventoryConstants.SKU_REGEX, message = InventoryConstants.INVALID_SKU_REGEX_MESSAGE)
    private String sku;

    @NotEmpty(message = "Product ID " + InventoryConstants.NOT_EMPTY_MESSAGE)
    @Pattern(regexp = ProductConstants.PRODUCT_ID_REGEX, message = ProductConstants.INVALID_PRODUCT_ID_MESSAGE)
    private String productId;

    @NotNull(message = "Stock " + InventoryConstants.NOT_NULL_MESSAGE)
    @Pattern(regexp = InventoryConstants.STOCK_REGEX, message = InventoryConstants.INVALID_STOCK_REGEX_MESSAGE)
    private String stock;


}
