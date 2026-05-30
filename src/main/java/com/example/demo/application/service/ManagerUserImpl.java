package com.example.demo.application.service;

import com.example.demo.application.exception.UserException;
import com.example.demo.domain.model.dtos.RegisterUserRequest;
import com.example.demo.domain.ports.in.ManagerUser;
import com.example.demo.domain.ports.out.H2Manager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Random;

@RequiredArgsConstructor
public class ManagerUserImpl implements ManagerUser {

    private static final Logger logger = LoggerFactory.getLogger(ManagerUserImpl.class);


    private final H2Manager h2Manager;

    @Override
    public void registerUser(RegisterUserRequest body) {

        boolean save = h2Manager.saveUser(body);

        if (!save) {
            new UserException("Aconteceu algum erro ao salvar o usuario", 500);
        }

        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String generatedLogin = new String(array, Charset.forName(body.name()));


        logger.info(" Nome gerado: ", generatedLogin);

        h2Manager.saveLogin(generatedLogin);

    }
}
