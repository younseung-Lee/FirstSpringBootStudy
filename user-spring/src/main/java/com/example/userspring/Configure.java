package com.example.userspring;

import com.example.userspring.domain.User;
import com.example.userspring.repository.JpaUserRepository;
import com.example.userspring.repository.UserRepository;
import com.example.userspring.service.UserService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configure {
    private final EntityManager entityManager;

    public Configure(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new JpaUserRepository(entityManager);
    }
}
