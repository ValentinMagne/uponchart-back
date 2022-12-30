package com.costardstudio.uponchart.controller;

import com.costardstudio.uponchart.entity.UserEntity;
import com.costardstudio.uponchart.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserEntity> getUsers() {
        return this.userService.getUsers();
    }
}
