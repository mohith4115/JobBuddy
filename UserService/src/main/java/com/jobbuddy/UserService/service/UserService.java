package com.jobbuddy.UserService.service;

import com.jobbuddy.UserService.dto.UserChangePassword;
import com.jobbuddy.UserService.dto.UserDto;
import com.jobbuddy.UserService.dto.UserPasswordProjection;
import com.jobbuddy.UserService.dto.UserValidation;
import com.jobbuddy.UserService.model.User;
import com.jobbuddy.UserService.repository.UserRepo;
import com.jobbuddy.UserService.utils.SHA256Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.ClassUtils.isPresent;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    public List<User> getAllUser(){
        return userRepo.findAll();
    }

    public Optional<User> getUser(String email){
        return userRepo.findByEmail(email);
    }

    public boolean validateUser(String email,String password){
        Optional<User> user = userRepo.getUserPassword(email);
        System.out.println(SHA256Converter.convertStringToSHA256(password) + "==" + user.get().getPassword_hash());
        if(user.get().getPassword_hash().equals(SHA256Converter.convertStringToSHA256(password))){
            System.out.println(SHA256Converter.convertStringToSHA256(password) + "==" + user.get().getPassword_hash());
            return true;
        }
        return false;
    }

    public String changePassword(UserChangePassword userChangePassword) {
        if(userChangePassword.newPassword.equals(userChangePassword.confirmPassword)){
            Optional<User> user = userRepo.getUserPassword(userChangePassword.email);
            if(user.isPresent() && user.get().getPassword_hash().equals(SHA256Converter.convertStringToSHA256(userChangePassword.password))){
                userRepo.updateUserPassword(userChangePassword.email,SHA256Converter.convertStringToSHA256(userChangePassword.newPassword));
                return "password updated successfully";
            }
            return "password is incorrect";
        }
        return "new password doesn't match current password";
    }

    public User saveUser(UserDto user){
        String password = user.getPassword_hash();
        User userdata = new User();
        userdata.setUsername(user.getUsername());
        userdata.setEmail(user.getEmail());
        userdata.setRole(user.getRole());
        userdata.setProfile(user.getProfile());
        userdata.setPassword_hash(SHA256Converter.convertStringToSHA256(password));
        return userRepo.save(userdata);
    }

    public String authenticateUser(UserValidation user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        if(authentication.isAuthenticated()){
            return "login success";
        }
        else{
            return "login failed";
        }
    }

}
