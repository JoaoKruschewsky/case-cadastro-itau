package com.example.demo.application.service;

import com.example.demo.application.exception.UserException;
import com.example.demo.adapter.dto.RegisterUserRequest;
import com.example.demo.adapter.dto.ResponserUser;
import com.example.demo.domain.ports.in.ManagerUser;
import com.example.demo.domain.ports.out.H2Manager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Random;

import static com.example.demo.application.service.LoginGenerator.generateLoginByFisrtNameAndLastName;
import static com.example.demo.application.service.LoginGenerator.generateLoginByFisrtNameAndOtherName;

@RequiredArgsConstructor
public class ManagerUserImpl implements ManagerUser {

    private static final Logger logger = LoggerFactory.getLogger(ManagerUserImpl.class);


    private final H2Manager h2Manager;

    @Override
    public ResponserUser registerUser(RegisterUserRequest body) {

       String login = "";
       ArrayList<String> cutNames = separatorNames(body.name());
       login = generateLoginByFisrtNameAndLastName(cutNames);
       Boolean save = h2Manager.saveUser(body, login);
       while (save.equals(false)) {
          login = generateLoginByFisrtNameAndOtherName(cutNames);
          save = h2Manager.saveUser(body, login);
       }

       logger.info("Login gerado: {}", login);

        if (!save) {
             throw new UserException("Aconteceu algum erro ao salvar o usuario", 500);
        }

        return new ResponserUser(login);

    }

    private String generatingLogin (ArrayList<String> names) {

        Random random = new Random();
        StringBuilder generateLogin = new StringBuilder(7);
        int count = 0;
        String getFisrtName = "";
        String getLastName = "";
        ArrayList<String> namesSelected = new ArrayList<>();

            count++;
            getFisrtName = names.get(0);
            logger.info("Pegando nome no array: " + getFisrtName);
            generateLogin.append(getFisrtName.substring(0, 3).replace(" ", ""));
            logger.info("Gerando uma parte do login: {}", generateLogin);
            getLastName = names.getLast();
            generateLogin.append(getFisrtName.substring(0, 3).replace(" ", ""));
            logger.info("Gerando uma parte do login: {}", generateLogin);
            logger.info("contagem: " + count);
            if (generateLogin.length() >= 7 ) {
                logger.info("entrou no break ");
            }



        return generateLogin.toString().toLowerCase().substring(0, 7);
    }

    private ArrayList<String> separatorNames (String name) {
        int space = 0;
        int beginIndex = 0;
        String nameInsert = "";
        ArrayList<String> namesSeparate = new ArrayList<>();

        logger.info("Name user: {} ", name);
        logger.info("Name Tamanho: {} ", name.length());
        for (int i = 0; i < name.length(); i++){
            logger.info("Entrei no for");

            Character letra = name.charAt(i);
            if (letra.equals(' ')) {
                logger.info("entrei no if");
                space = i;
                System.out.println("Contagem de espacos: " + space);
                nameInsert = name.substring(beginIndex, space);
                logger.info("Nome pra inserir: {}", nameInsert);
                namesSeparate.add(nameInsert);
                beginIndex = space + 1;
            }
        }
        logger.info("Sair do for");
        String lastName = name.substring(space).replace(" ", "");
        namesSeparate.add(lastName);

        for (String names : namesSeparate){
            logger.info("Nomes separados adicionados no array: {}", names);
        }

        return namesSeparate;
    }
}
