package com.kunsas.grabgoods.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {

    private String statusCode;
    private String statusMessage;
}
