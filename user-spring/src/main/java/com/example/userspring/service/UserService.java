package com.example.userspring.service;

import com.example.userspring.domain.User;
import com.example.userspring.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
//@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원 가입
    public String join(User user) {
        validateDuplicateUser(user);
        userRepository.add(user);
        return user.getId();
    }

    // 아이디 중복 확인
    private void validateDuplicateUser(User user) {
        userRepository.findById(user.getId()).ifPresent(existingUser -> {
            throw new IllegalStateException("이미 존재하는 사용자입니다.");
        });
    }

    // 전체 회원 조회
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    // 아이디 하나 조회
    public Optional<User> findOne(String id) {
        return userRepository.findById(id);
    }
}
