package com.example.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.dto.UserDto;
import com.example.app.entity.Address;
import com.example.app.entity.User;
import com.example.app.mapper.AddressMapper;
import com.example.app.mapper.UserMapper;
import com.example.app.repository.AddressRepository;
import com.example.app.repository.UserRepository;
import com.example.app.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);

        if (user.getAddress() != null) {
            // Guarantee new address creation by clearing any incoming ID
            user.getAddress().setId(null);
            
            // Persist the Address entity explicitly first
            Address savedAddress = addressRepository.save(user.getAddress());
            
            // Attach the saved Address (with database-generated ID) to the User
            user.setAddress(savedAddress);
        }

        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> fetchAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserDto)
                .toList();
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updatedUserDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        existingUser.setFirstName(updatedUserDto.getFirstName());
        existingUser.setLastName(updatedUserDto.getLastName());
        existingUser.setEmail(updatedUserDto.getEmail());
        existingUser.setRole(updatedUserDto.getRole());

        if (updatedUserDto.getAddress() != null) {
            if (existingUser.getAddress() == null) {
                Address newAddress = AddressMapper.mapToAddress(updatedUserDto.getAddress());
                newAddress.setId(null);
                Address savedAddress = addressRepository.save(newAddress);
                existingUser.setAddress(savedAddress);
            } else {
                Address address = existingUser.getAddress();
                address.setStreet(updatedUserDto.getAddress().getStreet());
                address.setCity(updatedUserDto.getAddress().getCity());
                address.setState(updatedUserDto.getAddress().getState());
                address.setCountry(updatedUserDto.getAddress().getCountry());
                address.setZipCode(updatedUserDto.getAddress().getZipCode());
            }
        }

        User updatedUserEntity = userRepository.save(existingUser);
        return UserMapper.mapToUserDto(updatedUserEntity);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        userRepository.delete(user);
    }
}