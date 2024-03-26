package com.example.userspring.service;

import com.example.userspring.domain.User;
import com.example.userspring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    // 회원 가입
    @Test
    void join() {
        // given
        User user = new User();
        user.setId("dcu");
        user.setPassword("1234");

        // when
        String id = userService.join(user);

        // then
        User findUser = userService.findOne(id).get();
        assertThat(user.getId()).isEqualTo(findUser.getId());
    }

    @Test
    public void duplicateUser() throws Exception {
        User user1 = new User();
        user1.setId("dcu");
        user1.setPassword("1234");
        User user2 = new User();
        user2.setId("dcu");
        user2.setPassword("1234");

        userService.join(user1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () ->
                userService.join(user2)
        );

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 사용자입니다.");
    }
}
