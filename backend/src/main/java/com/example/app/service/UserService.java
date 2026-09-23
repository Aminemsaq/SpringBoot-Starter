package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.dto.UserDto;

@Service
public  interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long userId);
    List<UserDto> fetchAllUsers();
    UserDto updateUser(Long userId, UserDto updatedUserDto);
    void deleteUser(Long userId);
}