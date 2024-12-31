package com.kunsas.grabgoods.inventoryservice.service;


import com.kunsas.grabgoods.inventoryservice.dto.InventoryDto;
import com.kunsas.grabgoods.inventoryservice.entity.Inventory;

import java.util.List;
import java.util.Optional;

public interface IInventoryService {
    void createInventory(InventoryDto inventoryDto);
    InventoryDto getInventoryById(String id);
    List<InventoryDto> getAllInventory();
    boolean updateInventory(String id, InventoryDto inventoryDto);
    boolean deleteInventory(String id);
    InventoryDto getStock(String productId);
    boolean updateStock(String productId, Long variation);

}
