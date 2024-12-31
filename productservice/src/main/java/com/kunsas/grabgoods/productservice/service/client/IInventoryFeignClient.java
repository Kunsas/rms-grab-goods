package com.kunsas.grabgoods.productservice.service.client;


import com.kunsas.grabgoods.productservice.dto.ResponseDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("inventoryservice")
public interface IInventoryFeignClient {

    @DeleteMapping("/api/inventory/{id}")
    public ResponseEntity<ResponseDto> deleteInventory(@PathVariable String id);
}
