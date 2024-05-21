package com.example.individualwebservice.controllers;

import com.example.individualwebservice.entities.User;
import com.example.individualwebservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping("/newuser")
    public ResponseEntity<User> createNewUser(@RequestBody User userInformation) {
        return ResponseEntity.ok(userService.createNewUser(userInformation));
    }
}
