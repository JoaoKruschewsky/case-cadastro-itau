package com.example.demo.adapter.in;


import com.example.demo.domain.model.dtos.RegisterUserRequest;
import com.example.demo.domain.model.dtos.ResponserUser;
import com.example.demo.domain.ports.in.ManagerUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("manager-controller/v1/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerUser managerUser;

    @PostMapping(path = "register")
    public ResponseEntity<ResponserUser> registerUser(@RequestBody RegisterUserRequest request){
        ResponserUser response = managerUser.registerUser(request);

        return ResponseEntity.ok(response);
    }
}
