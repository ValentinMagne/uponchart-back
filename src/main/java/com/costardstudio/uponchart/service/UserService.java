package com.costardstudio.uponchart.service;

import com.costardstudio.uponchart.entity.UserEntity;
import com.costardstudio.uponchart.models.User;
import com.costardstudio.uponchart.models.UserAccessToken;
import com.costardstudio.uponchart.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return this.userRepository.findAll().stream().map(User::new).collect(Collectors.toList());
    }

    public void saveAccessToken(UserAccessToken userAccessToken) throws UserPrincipalNotFoundException {
        UserEntity user = this.getCurrentUser();
        user.setAccessToken(userAccessToken.getAccess_token());
        if (userAccessToken.getRefresh_token() != null) {
            user.setRefreshToken(userAccessToken.getRefresh_token());
        }
        this.userRepository.save(user);
    }

    public UserEntity getCurrentUser() throws UserPrincipalNotFoundException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        return this.userRepository.findByLogin(username).orElseThrow(() -> new UserPrincipalNotFoundException("User not found"));
    }
}
