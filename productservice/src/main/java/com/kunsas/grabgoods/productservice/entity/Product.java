package com.kunsas.grabgoods.productservice.entity;

import com.kunsas.grabgoods.productservice.dto.client.CategoryResponseDto;
import com.kunsas.grabgoods.productservice.dto.client.ReviewResponseDto;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "products")
public class Product extends BaseEntity {

    @Id
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private List<CategoryResponseDto> categories;
    private List<ReviewResponseDto> reviews;

}
