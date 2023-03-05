package com.costardstudio.uponchart.controller;

import com.costardstudio.uponchart.entity.UserEntity;
import com.costardstudio.uponchart.repository.UserRepository;
import com.costardstudio.uponchart.security.AuthenticationRequest;
import com.costardstudio.uponchart.security.AuthenticationResponse;
import com.costardstudio.uponchart.security.RegisterRequest;
import com.costardstudio.uponchart.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {

    private final LoginService service;
    private final UserRepository userRepository;

    public LoginController(LoginService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        Optional<UserEntity> existingUser = this.userRepository.findByLogin(request.getLogin());
        if (existingUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(service.register(request));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }


}
