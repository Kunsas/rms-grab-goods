package com.kunsas.grabgoods.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDto {

    private String statusCode;
    private String statusMessage;
}
