package com.kunsas.grabgoods.categoryservice.dto;

import com.kunsas.grabgoods.categoryservice.constant.CategoryConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CategoryLookupRequestDto {

    @NotEmpty(message = "Category Name List" + CategoryConstants.NOT_EMPTY_MESSAGE)
    private List<
            @NotEmpty(message = "Category Name" + CategoryConstants.NOT_EMPTY_MESSAGE)
            @Size(min=1, max = 68, message = CategoryConstants.INVALID_CATEGORY_NAME_MESSAGE)
            @Pattern(regexp = CategoryConstants.CATEGORY_NAME_REGEX, message = CategoryConstants.INVALID_CATEGORY_NAME_MESSAGE)
                    String> names;
}
