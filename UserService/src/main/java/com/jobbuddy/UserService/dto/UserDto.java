package com.jobbuddy.UserService.dto;

import com.jobbuddy.UserService.model.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private String username;
    private String email;
    private String password_hash;
    private String role;
    private Profile profile;
}
