package com.kunsas.grabgoods.productservice.dto;

import com.kunsas.grabgoods.productservice.constant.ProductConstants;
import com.kunsas.grabgoods.productservice.constant.client.CategoryConstants;
import com.kunsas.grabgoods.productservice.constant.client.ReviewConstants;
import com.kunsas.grabgoods.productservice.dto.client.ReviewResponseDto;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Data
public class ProductRequestDto {

    @NotEmpty(message = "Product Name" + ProductConstants.NOT_EMPTY_MESSAGE)
    @Size(min=1, max = 100, message = ProductConstants.INVALID_PRODUCT_NAME_MESSAGE)
    @Pattern(regexp = ProductConstants.PRODUCT_NAME_REGEX, message = ProductConstants.INVALID_PRODUCT_NAME_MESSAGE)
    private String name;

    @NotEmpty(message = "Product Description" + ProductConstants.NOT_EMPTY_MESSAGE)
    @Size(min=1, max = 200, message = ProductConstants.INVALID_PRODUCT_DESCRIPTION_MESSAGE)
    @Pattern(regexp = ProductConstants.PRODUCT_DESCRIPTION_REGEX, message = ProductConstants.INVALID_PRODUCT_DESCRIPTION_MESSAGE)
    private String description;

    private String imageUrl;

    @NotNull(message = "Product Price" + ProductConstants.NOT_NULL_MESSAGE)
    @Min(value = 0, message = ProductConstants.INVALID_PRODUCT_PRICE_MESSAGE)
    @Pattern(regexp = ProductConstants.PRODUCT_PRICE_REGEX, message = ProductConstants.INVALID_PRODUCT_PRICE_MESSAGE)
    private String price;

    @NotEmpty(message = "Category Name List" + CategoryConstants.NOT_EMPTY_MESSAGE)
    private List<
            @NotEmpty(message = "Category Name" + CategoryConstants.NOT_EMPTY_MESSAGE)
            @Size(min=1, max = 68, message = CategoryConstants.INVALID_CATEGORY_NAME_MESSAGE)
            @Pattern(regexp = CategoryConstants.CATEGORY_NAME_REGEX, message = CategoryConstants.INVALID_CATEGORY_NAME_MESSAGE)
                    String> categories;

    @NotNull(message = "Review List" + ReviewConstants.NOT_NULL_MESSAGE)
    private List<ReviewResponseDto> reviews;
}
