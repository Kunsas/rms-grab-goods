package com.kunsas.grabgoods.userservice.dto;

import com.kunsas.grabgoods.userservice.constant.UserConstants;
import com.kunsas.grabgoods.userservice.enumeration.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewUserDto {
    
    @NotEmpty(message = "Name" + UserConstants.NOT_EMPTY_MESSAGE)
    @Size(min=8, max = 32, message = UserConstants.INVALID_USERNAME_MESSAGE)
    private String name;
    
    @NotEmpty(message = "Email" + UserConstants.NOT_EMPTY_MESSAGE)
    @Email(regexp = "^(?!.*\\.\\.)[a-zA-Z0-9.]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}$\n", message = UserConstants.INVALID_EMAIL_MESSAGE)
    private String email;
    
    @NotEmpty(message = "Password" + UserConstants.NOT_EMPTY_MESSAGE)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$\n", message = UserConstants.INVALID_PASSWORD_MESSAGE)
    private String password;
    
    @NotEmpty(message = "Role" + UserConstants.NOT_EMPTY_MESSAGE)
    private UserRole role;
    
    @NotEmpty(message = "Mobile number" + UserConstants.NOT_EMPTY_MESSAGE)
    @Pattern(regexp = "(^$|[0-9]{10})", message = UserConstants.INVALID_MOBILE_NUMBER_MESSAGE)
    private String mobileNumber;
    
    @NotEmpty(message = "Image" + UserConstants.NOT_EMPTY_MESSAGE)
    private String imageUrl;
    
    @NotEmpty(message = "Address" + UserConstants.NOT_EMPTY_MESSAGE)
    private String address;

}
