package com.example.userspring.repository;

import com.example.userspring.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class MemoryUserRepositoryTest {
    MemoryUserRepository repository = new MemoryUserRepository();

    @AfterEach
    public void afterEach() {
        repository.clearUser();
    }

    @Test
    public void add() {
        // given
        User user = new User();
        user.setId("dcu");

        // when
        repository.add(user);

        // then
        User result = repository.findById(user.getId()).get();
        assertThat(result).isEqualTo(user);
    }

    @Test
    public void findById() {
        User user1 = new User();
        user1.setId("user1");
        repository.add(user1);
        User user2 = new User();
        user2.setId("user2");
        repository.add(user2);

        User result = repository.findById("user1").get();

        assertThat(result).isEqualTo(user1);
    }

    @Test
    public void findAll() {
        User user1 = new User();
        user1.setId("user1");
        repository.add(user1);
        User user2 = new User();
        user2.setId("user2");
        repository.add(user2);

        List<User> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
