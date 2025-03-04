package com.jobbuddy.UserService.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @NotBlank
    private String username;
    @Email
    private String email;
    private String password_hash;
    private String role;
    private Profile profile;
}

