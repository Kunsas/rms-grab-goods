package com.kunsas.grabgoods.inventoryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class OutOfStockException extends RuntimeException{
    public OutOfStockException(String message){
        super(message);
    }
}
