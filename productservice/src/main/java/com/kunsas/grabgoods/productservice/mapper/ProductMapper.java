package com.kunsas.grabgoods.productservice.mapper;

import com.kunsas.grabgoods.productservice.dto.ProductRequestDto;
import com.kunsas.grabgoods.productservice.dto.ProductResponseDto;
import com.kunsas.grabgoods.productservice.dto.client.CategoryLookupRequestDto;
import com.kunsas.grabgoods.productservice.dto.client.CategoryResponseDto;
import com.kunsas.grabgoods.productservice.dto.client.ReviewResponseDto;
import com.kunsas.grabgoods.productservice.entity.Product;

import java.util.List;

public class ProductMapper {

    public static ProductResponseDto mapToProductResponseDto(Product product, List<String> categories, List<ReviewResponseDto> reviews) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setImageUrl(product.getImageUrl());
        productResponseDto.setPrice(String.valueOf(product.getPrice()));
        productResponseDto.setCategories(categories);
        productResponseDto.setReviews(reviews);

        return productResponseDto;
    }

    public static Product mapToProduct(ProductRequestDto productRequestDto, List<CategoryResponseDto> categories, List<ReviewResponseDto> reviews, Product product) {
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setImageUrl((productRequestDto.getImageUrl()));
        product.setPrice(Double.valueOf(productRequestDto.getPrice()));
        product.setCategories(categories);
        product.setReviews(reviews);

        return product;
    }

    public static Product mapToNewProduct(ProductRequestDto productRequestDto, List<CategoryResponseDto> categories, List<ReviewResponseDto> reviews) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setImageUrl((productRequestDto.getImageUrl()));
        product.setPrice(Double.valueOf(productRequestDto.getPrice()));
        product.setCategories(categories);
        product.setReviews(reviews);

        return product;
    }

    public static CategoryLookupRequestDto mapToCategoryLookupRequestDto(List<String> categories) {
        CategoryLookupRequestDto categoryLookupRequestDto = new CategoryLookupRequestDto();
        categoryLookupRequestDto.setNames(categories);

        return categoryLookupRequestDto;
    }

}
