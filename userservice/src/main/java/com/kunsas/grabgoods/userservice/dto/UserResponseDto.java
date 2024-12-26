package com.kunsas.grabgoods.userservice.dto;

import com.kunsas.grabgoods.userservice.constant.UserConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserResponseDto {

    @NotEmpty(message = "User ID" + UserConstants.NOT_EMPTY_MESSAGE)
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$", message = UserConstants.INVALID_USER_ID_MESSAGE)
    private String id;

    @NotEmpty(message = "Name" + UserConstants.NOT_EMPTY_MESSAGE)
    @Size(min=8, max = 32, message = UserConstants.INVALID_USERNAME_MESSAGE)
    private String name;

    @NotEmpty(message = "Email" + UserConstants.NOT_EMPTY_MESSAGE)
    @Email(regexp = "^(?!.*\\.\\.)[a-zA-Z0-9.]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}$", message = UserConstants.INVALID_EMAIL_MESSAGE)
    private String email;

    @NotEmpty(message = "Role" + UserConstants.NOT_EMPTY_MESSAGE)
    private String role;

    @NotEmpty(message = "Mobile number" + UserConstants.NOT_EMPTY_MESSAGE)
    @Pattern(regexp = "(^$|[0-9]{10})", message = UserConstants.INVALID_MOBILE_NUMBER_MESSAGE)
    private String mobileNumber;

    @NotEmpty(message = "Image" + UserConstants.NOT_EMPTY_MESSAGE)
    private String imageUrl;

    @NotEmpty(message = "Address" + UserConstants.NOT_EMPTY_MESSAGE)
    private String address;

}
