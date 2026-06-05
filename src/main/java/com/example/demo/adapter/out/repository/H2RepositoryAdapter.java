package com.example.demo.adapter.out.repository;

import com.example.demo.adapter.dto.ResponseUser;
import com.example.demo.application.exception.UserException;
import com.example.demo.adapter.dto.RegisterUserRequest;
import com.example.demo.application.service.ManagerUserImpl;
import com.example.demo.domain.model.entity.User;
import com.example.demo.domain.ports.out.H2Manager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.demo.adapter.out.repository.mapper.UserMapper.parseToUser;

@RequiredArgsConstructor
@Component
public class H2RepositoryAdapter implements H2Manager {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(ManagerUserImpl.class);

    @Override
    public User getUser(String login) {
        logger.info("Login recebido: {}", login);
        return userRepository.findByLoginName(login.trim()).orElseThrow(() -> new UserException("Usuario nao cadastrado", 401));
    }

    @Override
    public boolean saveUser(RegisterUserRequest user, String login) {


        if (userRepository.findByEmail(user.email()).isPresent()) {
           throw new UserException("Email Cadastrado", 401);
        } if (userRepository.findByCPF(user.cpf()).isPresent()) {
           throw new UserException("CPF cadastrado" , 401);
        }

        if ( userRepository.findByLoginName(login).isPresent()){
            logger.info("Login: {} -> já existe, gerando um novo..." , login);
            return false;
        }
        userRepository.save(parseToUser(user, login));

        return true;

    }

}
