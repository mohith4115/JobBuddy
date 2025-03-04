package com.jobbuddy.UserService.service;

import com.jobbuddy.UserService.model.User;
import com.jobbuddy.UserService.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
}
