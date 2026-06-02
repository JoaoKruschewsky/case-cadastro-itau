package com.example.demo.adapter.out.repository;

import com.example.demo.application.exception.UserException;
import com.example.demo.adapter.dto.RegisterUserRequest;
import com.example.demo.domain.ports.out.H2Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.demo.adapter.out.repository.mapper.UserMapper.parseToUser;

@RequiredArgsConstructor
@Component
public class H2RepositoryAdapter implements H2Manager {

    private final UserRepository userRepository;

    @Override
    public boolean saveUser(RegisterUserRequest user, String login) {

       boolean getUser = userRepository.findByEmail(user.email()).isPresent();
       boolean getUserByCpf = userRepository.findByCPF(user.cpf()).isPresent();

        if (getUser) {
           throw new UserException("Email Cadastrado", 401);
        } if (getUserByCpf) {
           throw new UserException("CPF cadastrado" , 401);
        }

        boolean getLogin = userRepository.findByLoginName(login).isPresent();
        if (getLogin){
            return false;
        }

        userRepository.save(parseToUser(user, login));

        return true;

    }

}
