package com.example.demo.domain.ports.out;

import com.example.demo.adapter.dto.RegisterUserRequest;
import com.example.demo.domain.model.User;

public interface H2Manager {

    boolean saveUser(RegisterUserRequest user, String login);
    User getUser(String login);
}
