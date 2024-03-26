package com.example.userspring.repository;

import com.example.userspring.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User add(User user);
    Optional<User> findById(String id);
    List<User> findAll();
}
