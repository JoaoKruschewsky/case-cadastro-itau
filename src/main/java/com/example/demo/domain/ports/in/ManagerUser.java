package com.example.demo.domain.ports.in;

import com.example.demo.adapter.dto.RegisterUserRequest;
import com.example.demo.adapter.dto.ResponserUser;

public interface ManagerUser {

    ResponserUser registerUser(RegisterUserRequest request);


}
