package com.cotrafa.prueba_tecnica.infrastructure.config;

import com.cotrafa.prueba_tecnica.application.UserService;
import com.cotrafa.prueba_tecnica.domain.user.ports.out.UserRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public UserService userService(UserRepositoryPort userRepositoryPort){
        return new UserService(userRepositoryPort);
    }
}
