package com.kunsas.grabgoods.inventoryservice.service;

import com.kunsas.grabgoods.inventoryservice.constant.InventoryConstants;
import com.kunsas.grabgoods.inventoryservice.dto.InventoryDto;
import com.kunsas.grabgoods.inventoryservice.entity.Inventory;
import com.kunsas.grabgoods.inventoryservice.exception.InventoryAlreadyExistsException;
import com.kunsas.grabgoods.inventoryservice.exception.InventoryNotFoundException;
import com.kunsas.grabgoods.inventoryservice.exception.OutOfStockException;
import com.kunsas.grabgoods.inventoryservice.mapper.InventoryMapper;
import com.kunsas.grabgoods.inventoryservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements IInventoryService {

    private InventoryRepository inventoryRepository;

    @Override
    public void createInventory(InventoryDto inventoryDto) {
        Optional<Inventory> existingInventory = inventoryRepository.findByProductId(inventoryDto.getProductId());
        if(existingInventory.isPresent()){
          throw new InventoryAlreadyExistsException(InventoryConstants.INVENTORY_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        } else {
            Inventory newInventory = InventoryMapper.mapToNewInventory(inventoryDto);
            inventoryRepository.save(newInventory);
        }
    }

    @Override
    public InventoryDto getInventoryById(String id) {
        Inventory inventory = inventoryRepository.findByProductId(id).orElseThrow(() -> new InventoryNotFoundException(InventoryConstants.INVENTORY_NOT_FOUND_EXCEPTION_MESSAGE));
        return InventoryMapper.mapToInventoryDto(inventory);
    }

    @Override
    public List<InventoryDto> getAllInventory() {
        return inventoryRepository.findAll().stream().map(
                InventoryMapper::mapToInventoryDto
        ).toList();
    }

    @Override
    public boolean updateInventory(String id, InventoryDto inventoryDto) {
        Inventory existingInventory = inventoryRepository.findByProductId(id).orElseThrow(()-> new InventoryNotFoundException(InventoryConstants.INVENTORY_NOT_FOUND_EXCEPTION_MESSAGE));
        Inventory updatedInventory = InventoryMapper.mapToInventory(inventoryDto, existingInventory);
        inventoryRepository.save(updatedInventory);
        return true;
    }

    @Override
    public boolean deleteInventory(String id) {
        Inventory inventory = inventoryRepository.findByProductId(id).orElseThrow(()-> new InventoryNotFoundException(InventoryConstants.INVENTORY_NOT_FOUND_EXCEPTION_MESSAGE));
        inventoryRepository.deleteById(inventory.getProductId());
        return true;
    }

    @Override
    public InventoryDto getStock(String productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow(() -> new InventoryNotFoundException(InventoryConstants.INVENTORY_NOT_FOUND_EXCEPTION_MESSAGE));
        return InventoryMapper.mapToInventoryDto(inventory);
    }

    @Override
    public boolean updateStock(String productId, Long variation) {
        Inventory existingInventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException(InventoryConstants.INVENTORY_NOT_FOUND_EXCEPTION_MESSAGE));

        if (existingInventory.getStock() < variation) {
            throw new OutOfStockException(InventoryConstants.OUT_OF_STOCK_MESSAGE);
        }
        existingInventory.setStock(existingInventory.getStock() - variation);

        Inventory updatedInventory = inventoryRepository.save(existingInventory);

        return true;
    }

}
