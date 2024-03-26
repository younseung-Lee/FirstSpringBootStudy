package com.example.userspring.controller;

import com.example.userspring.domain.User;
import com.example.userspring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String login() {
        return "users/login";
    }

    @GetMapping("/users/join")
    public String join() {
        return "users/join";
    }

    @PostMapping("/users/join")
    public String join(UserForm userForm) {
        User user = new User();
        user.setId(userForm.getId());
        user.setPassword(userForm.getPassword());

        userService.join(user);

        return "redirect:/";
    }

    @GetMapping("/users/userList")
    public String userList(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "users/userList";
    }
}