package com.costardstudio.uponchart.models;

import com.costardstudio.uponchart.entity.AuthorityEntity;
import com.costardstudio.uponchart.entity.UserEntity;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class User {
    UUID id;
    String login;
    Set<String> authorities;

    public User() {
    }

    public User(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.login = userEntity.getLogin();
        this.authorities = userEntity.getAuthorities().stream().map(AuthorityEntity::getName).collect(Collectors.toSet());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
}
