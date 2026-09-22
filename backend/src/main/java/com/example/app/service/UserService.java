package com.example.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.entity.User;

@Service
public class UserService {
    private final List<User> userList = new ArrayList<>();
    private Long nextId = 1L;

    public List<User> fetchAllUsers() {
        return userList;
    }

    public String addUser(User user) {
        user.setId(nextId++);
        userList.add(user);
        return "User Added!@";
    }

    public User fetchUser(Long id) {
        for (User user : userList) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    public Boolean updateUser(Long id, User updatedUser) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    return true;
                }).orElse(false);
    }

    public Boolean deleteUser(Long id) {
        return userList.removeIf(user -> user.getId().equals(id));
    }
}