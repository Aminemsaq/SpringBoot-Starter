package com.example.app.mapper;

import com.example.app.dto.UserDto;
import com.example.app.entity.User;

public class UserMapper {
    

    public static UserDto mapToUserDto(User user)
    {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

     public static User mapToUser(User userDto)
    {
        return new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
    }
}
