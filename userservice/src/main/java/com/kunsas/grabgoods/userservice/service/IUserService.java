package com.kunsas.grabgoods.userservice.service;

import com.kunsas.grabgoods.userservice.dto.UserRequestDto;
import com.kunsas.grabgoods.userservice.dto.UserResponseDto;

import java.util.List;

public interface IUserService {
    void createUser(UserRequestDto userRequestDto);
    UserResponseDto getUserById(String id);
    List<UserResponseDto> getAllUsers();
    boolean updateUser(String id, UserRequestDto userRequestDto);
    boolean deleteUser(String id);
    boolean changePassword(String id, String oldPassword, String newPassword);

}
