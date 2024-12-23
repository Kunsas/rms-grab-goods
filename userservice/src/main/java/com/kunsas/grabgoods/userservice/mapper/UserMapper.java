package com.kunsas.grabgoods.userservice.mapper;

import com.kunsas.grabgoods.userservice.dto.NewUserDto;
import com.kunsas.grabgoods.userservice.dto.UserDto;
import com.kunsas.grabgoods.userservice.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user, UserDto userDto){
        userDto.setId(String.valueOf(user.getId()));
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setMobileNumber(user.getMobileNumber());
        userDto.setImageUrl(user.getImageUrl());
        userDto.setAddress(user.getAddress());

        return userDto;
    }

    public static User mapToUser(UserDto userDto, User user){
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setMobileNumber(userDto.getMobileNumber());
        user.setImageUrl(userDto.getImageUrl());
        user.setAddress(userDto.getAddress());

        return user;
    }

    public static User mapToNewUser(NewUserDto newUserDto, User user){
        user.setName(newUserDto.getName());
        user.setEmail(newUserDto.getEmail());
        user.setPassword(newUserDto.getPassword());
        user.setRole(newUserDto.getRole());
        user.setMobileNumber(newUserDto.getMobileNumber());
        user.setImageUrl(newUserDto.getImageUrl());
        user.setAddress(newUserDto.getAddress());

        return user;
    }
}
