package com.example.demo.domain.ports.out;

import com.example.demo.domain.model.dtos.RegisterUserRequest;

public interface H2Manager {

    boolean saveUser(RegisterUserRequest user, String login);
    Boolean saveLogin(String login);
}
