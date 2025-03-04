package com.jobbuddy.UserService.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document
public class Profile {
    private List<String> skills;
    private String experience;
    private String resume;
}