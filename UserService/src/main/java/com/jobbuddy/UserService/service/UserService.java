package com.jobbuddy.UserService.service;

import com.jobbuddy.UserService.dto.UserPasswordProjection;
import com.jobbuddy.UserService.model.User;
import com.jobbuddy.UserService.repository.UserRepo;
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
        Optional<UserPasswordProjection> user = userRepo.getUserPassword(email);
        if(user.isPresent()){
            System.out.println(user);
        }
        return false;
    }
}
