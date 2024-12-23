package com.kunsas.grabgoods.userservice.service;

import com.kunsas.grabgoods.userservice.dto.NewUserDto;
import com.kunsas.grabgoods.userservice.dto.UserDto;

import java.util.List;

public interface IUserService {
    void createUser(NewUserDto newUserDto);
    UserDto getUserById(String id);
    List<UserDto> getAllUsers();
    boolean updateUser(String id, UserDto userDto);
    boolean deleteUser(String id);
    boolean changePassword(String id, String oldPassword, String newPassword);

}
