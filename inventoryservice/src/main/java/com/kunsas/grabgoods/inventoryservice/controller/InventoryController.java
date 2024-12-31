package com.kunsas.grabgoods.inventoryservice.controller;

import com.kunsas.grabgoods.inventoryservice.constant.InventoryConstants;
import com.kunsas.grabgoods.inventoryservice.constant.client.ProductConstants;
import com.kunsas.grabgoods.inventoryservice.dto.InventoryConfigInfoDto;
import com.kunsas.grabgoods.inventoryservice.dto.InventoryDto;
import com.kunsas.grabgoods.inventoryservice.dto.ResponseDto;
import com.kunsas.grabgoods.inventoryservice.service.IInventoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/inventory/", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class InventoryController {

    @Autowired
    private IInventoryService inventoryService;

    @Autowired
    private InventoryConfigInfoDto inventoryConfigInfoDto;

    @PostMapping
    public ResponseEntity<ResponseDto> createInventory(@Valid @RequestBody InventoryDto inventoryDto){
        inventoryService.createInventory(inventoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(InventoryConstants.STATUS_201, InventoryConstants.MESSAGE_201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDto> getInventoryById(@Pattern(regexp = ProductConstants.PRODUCT_ID_REGEX, message = ProductConstants.INVALID_PRODUCT_ID_MESSAGE) @PathVariable String id){
        InventoryDto inventoryDto = inventoryService.getInventoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(inventoryDto);
    }

    @GetMapping
    public ResponseEntity<List<InventoryDto>> getAllInventory(){
        List<InventoryDto> inventoryDtoList = inventoryService.getAllInventory();
        return ResponseEntity.status(HttpStatus.OK).body(inventoryDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateInventory(@Pattern(regexp = ProductConstants.PRODUCT_ID_REGEX, message = ProductConstants.INVALID_PRODUCT_ID_MESSAGE) @PathVariable String id, @Valid @RequestBody InventoryDto inventoryDto){
        boolean isUpdated = inventoryService.updateInventory(id, inventoryDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(InventoryConstants.STATUS_200, InventoryConstants.MESSAGE_200));
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(InventoryConstants.STATUS_500, InventoryConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteInventory(@Pattern(regexp = ProductConstants.PRODUCT_ID_REGEX, message = ProductConstants.INVALID_PRODUCT_ID_MESSAGE) @PathVariable String id){
        boolean isDeleted = inventoryService.deleteInventory(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(InventoryConstants.STATUS_200, InventoryConstants.MESSAGE_200));
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(InventoryConstants.STATUS_500, InventoryConstants.MESSAGE_500));
        }
    }

    @GetMapping("/config-info")
    public ResponseEntity<InventoryConfigInfoDto> getConfigInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryConfigInfoDto);
    }

    @GetMapping("/stock/{productId}")
    public ResponseEntity<InventoryDto> getStock(@Pattern(regexp = ProductConstants.PRODUCT_ID_REGEX, message = ProductConstants.INVALID_PRODUCT_ID_MESSAGE) @PathVariable String productId){
        InventoryDto inventoryDto = inventoryService.getStock(productId);
        return ResponseEntity.status(HttpStatus.OK).body(inventoryDto);
    }

    @PostMapping("/stock/{productId}")
    public ResponseEntity<ResponseDto> updateStock(@Pattern(regexp = ProductConstants.PRODUCT_ID_REGEX, message = ProductConstants.INVALID_PRODUCT_ID_MESSAGE) @PathVariable String productId, @Pattern(regexp = InventoryConstants.STOCK_REGEX, message = InventoryConstants.INVALID_STOCK_REGEX_MESSAGE) @PathVariable Long variation){
        boolean isUpdated = inventoryService.updateStock(productId, variation);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(InventoryConstants.STATUS_200, InventoryConstants.MESSAGE_200));
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(InventoryConstants.STATUS_500, InventoryConstants.MESSAGE_500));
        }
    }

}
