package com.kunsas.grabgoods.inventoryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory extends BaseEntity{

    private String sku;

    @Id
    private String productId;

    private Long stock;

}
