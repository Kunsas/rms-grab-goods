package com.kunsas.grabgoods.userservice.service;

import com.kunsas.grabgoods.userservice.constant.UserConstants;
import com.kunsas.grabgoods.userservice.dto.UserRequestDto;
import com.kunsas.grabgoods.userservice.dto.UserResponseDto;
import com.kunsas.grabgoods.userservice.entity.User;
import com.kunsas.grabgoods.userservice.exception.UserAlreadyExistsException;
import com.kunsas.grabgoods.userservice.exception.UserNotFoundException;
import com.kunsas.grabgoods.userservice.mapper.UserMapper;
import com.kunsas.grabgoods.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService{

    private UserRepository userRepository;

    @Override
    public void createUser(UserRequestDto userRequestDto) {
        Optional<User> existingUser = userRepository.findByEmail(userRequestDto.getEmail());
        if(existingUser.isPresent()){
          throw new UserAlreadyExistsException(UserConstants.USER_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        } else {
            User newUser = UserMapper.mapToNewUser(userRequestDto, new User());
            userRepository.save(newUser);
        }
    }

    @Override
    public UserResponseDto getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(UserConstants.USER_NOT_FOUND_EXCEPTION_MESSAGE));
        return UserMapper.mapToUserDto(user, new UserResponseDto());
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(
                user -> UserMapper.mapToUserDto(user, new UserResponseDto())
        ).toList();
    }

    @Override
    public boolean updateUser(String id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(UserConstants.USER_NOT_FOUND_EXCEPTION_MESSAGE));
        User updateUser = UserMapper.mapToUser(userRequestDto, user);
        userRepository.save(updateUser);
        return true;
    }

    @Override
    public boolean deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(UserConstants.USER_NOT_FOUND_EXCEPTION_MESSAGE));
        userRepository.deleteById(String.valueOf(user.getId()));
        return true;
    }

    @Override
    public boolean changePassword(String id, String oldPassword, String newPassword) {
        return false;
    }


}
