package com.example.demo.adapter.in;


import com.example.demo.adapter.dto.RegisterUserRequest;
import com.example.demo.adapter.dto.ResponserUser;
import com.example.demo.domain.ports.in.ManagerUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("manager-controller/v1/manager")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerUser managerUser;

    @PostMapping(path = "register")
    public ResponseEntity<ResponserUser> registerUser(@RequestBody RegisterUserRequest request){
        ResponserUser response = managerUser.registerUser(request);

        return ResponseEntity.ok(response);
    }
}
