package com.kunsas.grabgoods.userservice.controller;

import com.kunsas.grabgoods.userservice.constant.UserConstants;
import com.kunsas.grabgoods.userservice.dto.UserRequestDto;
import com.kunsas.grabgoods.userservice.dto.UserConfigInfoDto;
import com.kunsas.grabgoods.userservice.dto.UserResponseDto;
import com.kunsas.grabgoods.userservice.dto.ResponseDto;
import com.kunsas.grabgoods.userservice.service.IUserService;
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
@RequestMapping(path = "/api/users", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserConfigInfoDto userConfigInfoDto;

    @PostMapping
    public ResponseEntity<ResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto){
        userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(UserConstants.STATUS_201, UserConstants.MESSAGE_201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$", message = UserConstants.INVALID_USER_ID_MESSAGE) @PathVariable String id){
        UserResponseDto userResponseDto = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> userResponseDtoList = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateUser(@Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$", message = UserConstants.INVALID_USER_ID_MESSAGE) @PathVariable String id, @Valid @RequestBody UserRequestDto userRequestDto){
        boolean isUpdated = userService.updateUser(id, userRequestDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(UserConstants.STATUS_500, UserConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteUser(@Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$", message = UserConstants.INVALID_USER_ID_MESSAGE) @PathVariable String id){
        boolean isDeleted = userService.deleteUser(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(UserConstants.STATUS_500, UserConstants.MESSAGE_500));
        }
    }

    @GetMapping("/config-info")
    public ResponseEntity<UserConfigInfoDto> getConfigInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(userConfigInfoDto);
    }


}
