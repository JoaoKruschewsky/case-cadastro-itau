package com.example.demo.domain.ports.in;

import com.example.demo.domain.model.dtos.RegisterUserRequest;
import com.example.demo.domain.model.dtos.ResponserUser;

public interface ManagerUser {

    ResponserUser registerUser(RegisterUserRequest request);


}
