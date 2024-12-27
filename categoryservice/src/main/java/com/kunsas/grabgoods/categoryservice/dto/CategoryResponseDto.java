package com.kunsas.grabgoods.categoryservice.dto;

import com.kunsas.grabgoods.categoryservice.constant.CategoryConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryResponseDto {

    @NotEmpty(message = "Category ID" + CategoryConstants.NOT_EMPTY_MESSAGE)
    @Pattern(regexp = CategoryConstants.CATEGORY_ID_REGEX, message = CategoryConstants.INVALID_CATEGORY_ID_MESSAGE)
    private String id;

    @NotEmpty(message = "Category Name" + CategoryConstants.NOT_EMPTY_MESSAGE)
    @Size(min=1, max = 68, message = CategoryConstants.INVALID_CATEGORY_NAME_MESSAGE)
    @Pattern(regexp = CategoryConstants.CATEGORY_NAME_REGEX, message = CategoryConstants.INVALID_CATEGORY_NAME_MESSAGE)
    private String name;
}
