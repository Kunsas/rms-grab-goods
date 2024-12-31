package com.kunsas.grabgoods.cartservice.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "cart")
public class Cart extends BaseEntity{

    @Id
    private String id;

}
