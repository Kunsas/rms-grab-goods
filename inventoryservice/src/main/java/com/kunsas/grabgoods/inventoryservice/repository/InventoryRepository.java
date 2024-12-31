package com.kunsas.grabgoods.inventoryservice.repository;

import com.kunsas.grabgoods.inventoryservice.entity.Inventory;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
    Optional<Inventory> findByProductId(String productId);
}
