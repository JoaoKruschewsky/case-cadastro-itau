package com.example.demo.application.service;

import com.example.demo.adapter.dto.ResponseUser;
import com.example.demo.application.exception.ApiException;
import com.example.demo.application.exception.UserException;
import com.example.demo.adapter.dto.RegisterUserRequest;
import com.example.demo.adapter.dto.ResponseLoginUser;
import com.example.demo.domain.model.User;
import com.example.demo.domain.ports.in.ManagerUser;
import com.example.demo.domain.ports.out.H2Manager;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.criteria.JpaOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static com.example.demo.application.usecase.LoginGeneratorUseCase.generateLoginByFisrtNameAndLastName;
import static com.example.demo.application.usecase.LoginGeneratorUseCase.generateLoginByFisrtNameAndOtherName;
import static com.example.demo.application.usecase.ValidationUseCase.validationUser;

@RequiredArgsConstructor
public class ManagerUserImpl implements ManagerUser {

    private static final Logger logger = LoggerFactory.getLogger(ManagerUserImpl.class);


    private final H2Manager h2Manager;

    @Override
    public ResponseUser getUser(String login) {
        User getUser = h2Manager.getUser(login);
        return new ResponseUser(
                getUser.getNomeCompleto(),
                getUser.getLoginName()
        );
    }

    @Override
    public ResponseLoginUser registerUser(RegisterUserRequest body) {

        validationUser(body);
       String login = "";
       ArrayList<String> cutNames = separatorNames(body.name());
       login = generateLoginByFisrtNameAndLastName(cutNames);
       Boolean save = h2Manager.saveUser(body, login);
       int count = 0;
       while (save.equals(false)) {
           count++;
          login = generateLoginByFisrtNameAndOtherName(cutNames, count);
          save = h2Manager.saveUser(body, login);
          logger.info("Login gerado: {}", login);

          if (count == 10) {
               throw new ApiException("Ocorreu um erro ao tentar gerar o login", 500);
          }

       }

       logger.info("Login gerado: {}", login);

        if (!save) {
             throw new UserException("Aconteceu algum erro ao salvar o usuário", 500);
        }

        return new ResponseLoginUser(login);

    }

    private ArrayList<String> separatorNames (String name) {
        int space = 0;
        int beginIndex = 0;
        String nameInsert = "";
        ArrayList<String> namesSeparate = new ArrayList<>();

        logger.info("Name user: {} ", name);
        logger.info("Name Tamanho: {} ", name.length());
        for (int i = 0; i < name.length(); i++){
            Character letra = name.charAt(i);
            if (letra.equals(' ')) {
                space = i;
                System.out.println("Index do espaco: " + space);
                nameInsert = name.substring(beginIndex, space);
                logger.info("Nome pra inserir: {}", nameInsert);
                namesSeparate.add(nameInsert);
                beginIndex = space + 1;
            }
        }
        String lastName = name.substring(space).replace(" ", "");
        logger.info("Ultimo nome: {}", lastName);
        namesSeparate.add(lastName);

        for (String names : namesSeparate){
            logger.info("Nomes separados adicionados no array: {}", names);
        }

        return namesSeparate;
    }
}
