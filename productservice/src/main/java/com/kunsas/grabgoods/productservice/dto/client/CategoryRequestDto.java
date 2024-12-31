package com.kunsas.grabgoods.productservice.dto.client;

import com.kunsas.grabgoods.productservice.constant.client.CategoryConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequestDto {

    @NotEmpty(message = "Category Name" + CategoryConstants.NOT_EMPTY_MESSAGE)
    @Size(min=1, max = 68, message = CategoryConstants.INVALID_CATEGORY_NAME_MESSAGE)
    @Pattern(regexp = CategoryConstants.CATEGORY_NAME_REGEX, message = CategoryConstants.INVALID_CATEGORY_NAME_MESSAGE)
    private String name;
}
