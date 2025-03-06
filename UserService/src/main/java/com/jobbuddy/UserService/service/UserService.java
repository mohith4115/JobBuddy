package com.jobbuddy.UserService.service;

import com.jobbuddy.UserService.dto.UserPasswordProjection;
import com.jobbuddy.UserService.model.User;
import com.jobbuddy.UserService.repository.UserRepo;
import com.jobbuddy.UserService.utils.SHA256Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.ClassUtils.isPresent;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public List<User> getAllUser(){
        return userRepo.findAll();
    }

    public Optional<User> getUser(String email){
        return userRepo.findByEmail(email);
    }

    public boolean validateUser(String email,String password){
        Optional<User> user = userRepo.getUserPassword(email);
//        System.out.println(SHA256Converter.convertStringToSHA256(password) + "==" + user.get().getPassword_hash());
        if(user.get().getPassword_hash().equals(SHA256Converter.convertStringToSHA256(password))){
            System.out.println(SHA256Converter.convertStringToSHA256(password) + "==" + user.get().getPassword_hash());
            return true;
        }
        return false;
    }
}
