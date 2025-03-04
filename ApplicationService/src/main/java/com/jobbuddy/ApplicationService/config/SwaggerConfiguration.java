package com.jobbuddy.ApplicationService.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class SwaggerConfiguration {
    @Value("spring.profiles.active")
    private String currentEnv;

    @Bean
    @Profile("dev")
    public OpenAPI customSwaggerApiDev(){
        return new OpenAPI().info(new Info().title("this is an example project - DEV").version("1.0").description("this is justa  starter project for testing"));
    }


    @Bean
    @Profile("prod")
    public OpenAPI customSwaggerApiProd(){
        return new OpenAPI().info(new Info().title("this is an example project -PROD").version("1.0").description("this is justa  starter project for testing"));
    }
}