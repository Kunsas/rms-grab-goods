package com.kunsas.grabgoods.userservice.mapper;

import com.kunsas.grabgoods.userservice.dto.UserRequestDto;
import com.kunsas.grabgoods.userservice.dto.UserResponseDto;
import com.kunsas.grabgoods.userservice.entity.User;
import com.kunsas.grabgoods.userservice.enumeration.UserRole;

public class UserMapper {

    public static UserResponseDto mapToUserDto(User user, UserResponseDto userResponseDto){
        userResponseDto.setId(String.valueOf(user.getId()));
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setRole(user.getRole().toValue());
        userResponseDto.setMobileNumber(user.getMobileNumber());
        userResponseDto.setImageUrl(user.getImageUrl());
        userResponseDto.setAddress(user.getAddress());

        return userResponseDto;
    }

    public static User mapToUser(UserRequestDto userRequestDto, User user){
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setRole(UserRole.fromValue(userRequestDto.getRole()));
        user.setMobileNumber(userRequestDto.getMobileNumber());
        user.setImageUrl(userRequestDto.getImageUrl());
        user.setAddress(userRequestDto.getAddress());

        return user;
    }

    public static User mapToNewUser(UserRequestDto userRequestDto, User user){
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setRole(UserRole.fromValue(userRequestDto.getRole()));
        user.setMobileNumber(userRequestDto.getMobileNumber());
        user.setImageUrl(userRequestDto.getImageUrl());
        user.setAddress(userRequestDto.getAddress());

        return user;
    }
}
