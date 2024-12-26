package com.kunsas.grabgoods.userservice.dto;

import com.kunsas.grabgoods.userservice.constant.UserConstants;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequestDto {
    
    @NotEmpty(message = "Name" + UserConstants.NOT_EMPTY_MESSAGE)
    @Size(min=8, max = 32, message = UserConstants.INVALID_USERNAME_MESSAGE)
    private String name;
    
    @NotEmpty(message = "Email" + UserConstants.NOT_EMPTY_MESSAGE)
    @Email(regexp = "^(?!.*\\.\\.)[a-zA-Z0-9.]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}", message = UserConstants.INVALID_EMAIL_MESSAGE)
    private String email;
    
    @NotEmpty(message = "Password" + UserConstants.NOT_EMPTY_MESSAGE)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}", message = UserConstants.INVALID_PASSWORD_MESSAGE)
    private String password;
    
    @NotNull(message = "Role" + UserConstants.NOT_NULL_MESSAGE)
    private String role;
    
    @NotEmpty(message = "Mobile number" + UserConstants.NOT_EMPTY_MESSAGE)
    @Pattern(regexp = "(^$|[0-9]{10})", message = UserConstants.INVALID_MOBILE_NUMBER_MESSAGE)
    private String mobileNumber;
    
    @NotEmpty(message = "Image" + UserConstants.NOT_EMPTY_MESSAGE)
    private String imageUrl;
    
    @NotEmpty(message = "Address" + UserConstants.NOT_EMPTY_MESSAGE)
    private String address;

}
