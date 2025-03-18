package com.jobbuddy.UserService.controller;

import com.jobbuddy.UserService.dto.UserChangePassword;
import com.jobbuddy.UserService.dto.UserDto;
import com.jobbuddy.UserService.dto.UserPasswordProjection;
import com.jobbuddy.UserService.dto.UserValidation;
import com.jobbuddy.UserService.model.User;
import com.jobbuddy.UserService.service.JwtService;
import com.jobbuddy.UserService.service.UserService;
import com.sun.source.doctree.SummaryTree;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("JobBuddy")
@Tag(name="User API",description = "handles all User CRUD operation on user data")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;


    @Operation(summary = "register new user",description = "registering new users into application")
    @PostMapping("/user/register")
    public User registerUser(@RequestBody UserDto user){
        return userService.saveUser(user);
    }

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

    @Operation(summary = "validate user", description = "checks the password of user")
    @PostMapping("/user/auth/")
    public boolean validateUser(@RequestBody UserValidation user){
        return userService.validateUser(user.getEmail(), user.getPassword());
    }

    @Operation(summary = "reset password",description = "api for resetting the password")
    @PostMapping("/user/changepassword")
    public String changePassword(@RequestBody UserChangePassword userChangePassword){
        return userService.changePassword(userChangePassword);
    }

    @Operation(summary = "user login",description = "login and get success message")
    @PostMapping("/user/login")
    public String userLogin(UserValidation user){
        return userService.authenticateUser(user);
    }

    @Operation(summary = "user login with token",description = "login and get jwt token")
    @PostMapping("/user/gettoken")
    public String userLoginToken(UserValidation user){
        return jwtService.generateToken(user.getEmail());
    }
}
