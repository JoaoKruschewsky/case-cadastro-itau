package com.example.demo.domain.ports.in;

import com.example.demo.adapter.dto.RegisterUserRequest;
import com.example.demo.adapter.dto.ResponseLoginUser;
import com.example.demo.adapter.dto.ResponseUser;

public interface ManagerUser {

    ResponseLoginUser registerUser(RegisterUserRequest request);
    ResponseUser getUser(String login);


}
