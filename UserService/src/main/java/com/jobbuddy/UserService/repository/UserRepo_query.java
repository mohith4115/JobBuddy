package com.jobbuddy.UserService.repository;

import com.jobbuddy.UserService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo_query extends MongoRepository<User,String> {

    @Query("{ 'email' : ?0 }")
    public Optional<User> findByEmail(String email);

}
