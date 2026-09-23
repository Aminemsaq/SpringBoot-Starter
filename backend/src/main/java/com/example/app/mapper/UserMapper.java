package com.example.app.mapper;

import com.example.app.dto.UserDto;
import com.example.app.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        if (user == null) return null;

        return new UserDto(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getRole(),
            AddressMapper.mapToAddressDto(user.getAddress())
        );
    }

    public static User mapToUser(UserDto userDto) {
        if (userDto == null) return null;

        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setAddress(AddressMapper.mapToAddress(userDto.getAddress()));

        return user;
    }
}