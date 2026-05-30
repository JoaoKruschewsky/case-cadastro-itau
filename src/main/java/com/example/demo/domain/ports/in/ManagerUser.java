package com.example.demo.domain.ports.in;

import com.example.demo.domain.model.dtos.RegisterUserRequest;

public interface ManagerUser {

    void registerUser(RegisterUserRequest request);


}
