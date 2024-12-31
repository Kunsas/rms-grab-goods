package com.kunsas.grabgoods.reviewservice.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "reviews")
public class Review extends BaseEntity{

    @Id
    private String id;

    private String userId;
    private String title;
    private String description;
    private Float rating;
    private String imageUrl;
    private String productId;

}
