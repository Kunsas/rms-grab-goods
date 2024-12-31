package com.kunsas.grabgoods.inventoryservice.mapper;

import com.kunsas.grabgoods.inventoryservice.dto.InventoryDto;
import com.kunsas.grabgoods.inventoryservice.entity.Inventory;

public class InventoryMapper {

    public static Inventory mapToInventory(InventoryDto inventoryDto, Inventory inventory){
        inventory.setSku(inventoryDto.getSku());
        inventory.setProductId(inventoryDto.getProductId());
        inventory.setStock(Long.valueOf(inventoryDto.getStock()));

        return inventory;
    }

    public static Inventory mapToNewInventory(InventoryDto inventoryDto){
        Inventory inventory = new Inventory();
        inventory.setSku(inventoryDto.getSku());
        inventory.setProductId(inventoryDto.getProductId());
        inventory.setStock(Long.valueOf(inventoryDto.getStock()));

        return inventory;
    }

    public static InventoryDto mapToInventoryDto(Inventory inventory){
        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setSku(inventory.getSku());
        inventoryDto.setProductId(inventory.getProductId());
        inventoryDto.setStock(String.valueOf(inventory.getStock()));

        return inventoryDto;
    }

}
