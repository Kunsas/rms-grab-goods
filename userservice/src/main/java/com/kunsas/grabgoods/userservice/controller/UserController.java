package com.kunsas.grabgoods.userservice.controller;

import com.kunsas.grabgoods.userservice.constant.UserConstants;
import com.kunsas.grabgoods.userservice.dto.NewUserDto;
import com.kunsas.grabgoods.userservice.dto.UserDto;
import com.kunsas.grabgoods.userservice.dto.UserResponseDto;
import com.kunsas.grabgoods.userservice.service.IUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
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

    private IUserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody NewUserDto newUserDto){
        userService.createUser(newUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseDto(UserConstants.STATUS_201, UserConstants.MESSAGE_201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$", message = UserConstants.INVALID_USER_ID_MESSAGE) @PathVariable String id){
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtoList = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$", message = UserConstants.INVALID_USER_ID_MESSAGE) @PathVariable String id, @Valid @RequestBody UserDto userDto){
        boolean isUpdated = userService.updateUser(id, userDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new UserResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserResponseDto(UserConstants.STATUS_500, UserConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<UserResponseDto> deleteUser(@Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$", message = UserConstants.INVALID_USER_ID_MESSAGE) @PathVariable String id){
        boolean isDeleted = userService.deleteUser(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new UserResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserResponseDto(UserConstants.STATUS_500, UserConstants.MESSAGE_500));
        }
    }


}
