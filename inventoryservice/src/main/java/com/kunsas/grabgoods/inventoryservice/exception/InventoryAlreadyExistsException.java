package com.kunsas.grabgoods.inventoryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class InventoryAlreadyExistsException extends RuntimeException{
    public InventoryAlreadyExistsException(String message){
        super(message);
    }
}
