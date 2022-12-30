package com.costardstudio.uponchart.service;

import com.costardstudio.uponchart.entity.UserEntity;
import com.costardstudio.uponchart.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getUsers() {
        return this.userRepository.findAll();
    }
}
