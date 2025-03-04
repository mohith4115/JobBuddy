package com.jobbuddy.UserService.controller;

import com.jobbuddy.UserService.model.User;
import com.jobbuddy.UserService.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("JobBuddy")
@Tag(name="User API",description = "handles all User CRUD operation on user data")
public class UserController {
    @Autowired
    UserService userService;

    @Operation(summary = "all users", description = "returns all the users in the database")
    @GetMapping("/user/all")
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @Operation(summary = "user with email id", description = "returns the user with given email")
    @GetMapping("/user/{email}")
    public Optional<User> getUser(@PathVariable  String email){
        return userService.getUser(email);
    }
}
