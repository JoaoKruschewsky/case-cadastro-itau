package com.example.demo.application.service;

import com.example.demo.application.exception.UserException;
import com.example.demo.domain.model.dtos.RegisterUserRequest;
import com.example.demo.domain.model.dtos.ResponserUser;
import com.example.demo.domain.ports.in.ManagerUser;
import com.example.demo.domain.ports.out.H2Manager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
public class ManagerUserImpl implements ManagerUser {

    private static final Logger logger = LoggerFactory.getLogger(ManagerUserImpl.class);


    private final H2Manager h2Manager;

    @Override
    public ResponserUser registerUser(RegisterUserRequest body) {

        boolean save = h2Manager.saveUser(body);

        if (!save) {
            new UserException("Aconteceu algum erro ao salvar o usuario", 500);
        }
        int space = 0;
        int beginIndex = 0;
        String name = "";
        ArrayList<String> namesSeparate = new ArrayList<>();
        logger.info(body.name());
        for (int i = 0; i < body.name().length(); i++){
            Character letra = body.name().charAt(i);
            if (letra.equals(' ')) {
                space = i;
                System.out.println(space);
                name = body.name().substring(beginIndex, space);
                namesSeparate.add(name);
            }
            beginIndex = space;

        }
        String lastName = body.name().substring(space);
        namesSeparate.add(lastName);


        Random random = new Random();
        StringBuilder generateLogin = new StringBuilder();
        int countCaughtName = 0;
        int targetStringLength = 7;
        String getNameInArray = "";
        String verifyName = "";
        for (int n = 0; n < namesSeparate.size(); n++) {
            countCaughtName++;

            int randomIndexName = random.nextInt(namesSeparate.size());
            getNameInArray = namesSeparate.get(randomIndexName);

            if (!verifyName.equals(getNameInArray)) {
                logger.info("Pegando nome no array: " + getNameInArray);
                generateLogin.append(getNameInArray.substring(0, 4));
                logger.info("gerando login" + generateLogin);
            }
            System.out.println("contagem: " + countCaughtName);
            if (countCaughtName == 2) {
                if (generateLogin.length() == 7) {
                    System.out.println("entrei aq");
                    break;
                } else {
                    System.out.println("entrei aq");
                    countCaughtName = 0;
                    continue;
                }
            }
            verifyName = getNameInArray;


        }






        System.out.print(generateLogin.length());
        System.out.println("Login gerado: " + generateLogin.toString().replace(" ", "").toLowerCase());










//        int leftLimit = 97;
//        int rightLimit = 122;
//        Random random = new Random();
//        StringBuilder buffer = new StringBuilder(targetStringLength);
//        for (int i = 0; i < targetStringLength; i++) {
//            int randomLimitedInt = leftLimit + (int)
//                    (random.nextFloat() * (rightLimit - leftLimit + 1));
//            buffer.append((char) randomLimitedInt);
//        }
//        body.name().indexOf()
//        byte[] array = new byte[7];
//        new Random().nextBytes(array);
//        String generatedLogin = new String(array, Charset.forName(body.name()));



        return new ResponserUser("s");

    }

    private String generatingLogin (String name) {



        return "";
    }
}
