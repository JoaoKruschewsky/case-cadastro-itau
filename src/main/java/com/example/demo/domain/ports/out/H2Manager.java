package com.example.demo.domain.ports.out;

import com.example.demo.adapter.dto.RegisterUserRequest;

public interface H2Manager {

    boolean saveUser(RegisterUserRequest user, String login);
}
