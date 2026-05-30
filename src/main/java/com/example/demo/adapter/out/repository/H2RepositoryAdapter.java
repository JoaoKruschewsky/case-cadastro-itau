package com.example.demo.adapter.out.repository;

import com.example.demo.application.exception.UserException;
import com.example.demo.domain.model.dtos.RegisterUserRequest;
import com.example.demo.domain.model.entity.Login;
import com.example.demo.domain.model.entity.User;
import com.example.demo.domain.ports.out.H2Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.demo.adapter.out.repository.mapper.UserMapper.parseToUser;

@RequiredArgsConstructor
@Component
public class H2RepositoryAdapter implements H2Manager {

    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    @Override
    public void saveLogin(String login) {

        Login loginBuild = Login.builder().login(login).build();

        userRepository.save(User.builder()
                .login(loginBuild).build());
    }

    @Override
    public boolean saveUser(RegisterUserRequest user) {

       boolean getUser = userRepository.findByEmail(user.email()).isPresent();

        if (getUser) {
           throw new UserException("Usuario Cadastrato", 401);
        }

        userRepository.save(parseToUser(user));


        return true;

    }
}
