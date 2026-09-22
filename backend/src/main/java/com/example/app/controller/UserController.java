package com.example.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.entity.User;
import com.example.app.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.fetchAllUsers(),
                HttpStatus.OK);
    }

    @PostMapping("/api/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("user added!@");
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> findUser(@PathVariable Long id) {
        if (userService.fetchUser(id) == null)
            return new ResponseEntity<>(userService.fetchUser(id), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(userService.fetchUser(id), HttpStatus.OK);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        boolean isUpdated = userService.updateUser(id, updatedUser);

        if (isUpdated) {
            return ResponseEntity.ok("User updated successfully!");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + id);
    }

    @DeleteMapping("api/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);

        if (isDeleted) {
            return ResponseEntity.ok("User deleted successfully!"); // HTTP 200 OK
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + id); // HTTP 404
    }
}