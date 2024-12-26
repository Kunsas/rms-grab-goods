package com.kunsas.grabgoods.categoryservice.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "categories")
public class Category extends BaseEntity{

    @Id
    private String id;
    private String name;
}
