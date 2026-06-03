package com.example.demo.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Random;

public class LoginGenerator {


    private static final Logger logger = LoggerFactory.getLogger(LoginGenerator.class);


    public static String generateLoginByFisrtNameAndLastName (ArrayList<String> names) {
        logger.info("Gerando login por primeiro nome e ultimo nome ");

        StringBuilder generateLogin = new StringBuilder(7);
        String getFisrtName = "";
        String getLastName = "";

        getFisrtName = names.get(0);
        logger.info("Pegando nome no array: " + getFisrtName);
        generateLogin.append(getFisrtName.substring(0, 3));
        logger.info("Gerando uma parte do login: {}", generateLogin);
        getLastName = names.getLast();
        generateLogin.append(getLastName.substring(0, 4));
        logger.info("Gerando uma parte do login: {}", generateLogin);



        return generateLogin.toString().toLowerCase().trim().replace(" ", "");

    }

    private static String generatingLoginWithRandomLetters(ArrayList<String> names) {
        logger.info("Gerando login com uma parte do primeiro nome e letras aleatorias");

        StringBuilder generateLogin = new StringBuilder(7);
        String firstName = names.get(0);

        generateLogin.append(firstName.substring(0, 2).toLowerCase());

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            char randomChar = (char) ('a' + random.nextInt(26));
            generateLogin.append(randomChar);
        }

        logger.info("Login gerando se tiver mais de 5 tentativas: {}", generateLogin);
        return generateLogin.toString().toLowerCase().trim().replace(" ", "");
    }


    public static String generateLoginByFisrtNameAndOtherName (ArrayList<String> names, int count) {

        logger.info("Gerando login por primeiro nome e outro nome ");
        if (count >= 5) {
            logger.info("Tentavia 5 ou mais");
            return generatingLoginWithRandomLetters(names);
        }
        StringBuilder generateLogin = new StringBuilder(7);
        String getFisrtName = "";
        String getOtherName = "";
        Random random = new Random();
        logger.info("Verificando se o nome é composto por mais de 2 nomes");
        if (names.size() > 2) {
            logger.info("Nome é composto por mais de 2 nomes");
            logger.info("Gerando login... ");
            logger.info("Adicionando primeiro nome");
            getFisrtName = names.get(0);
            generateLogin.append(getFisrtName.substring(0, 3));
            logger.info("Primeiro nome  adicionado: {} ", generateLogin.toString());

            for (int i = 1; i < names.size() - 1; i++) {
                int sortOtherName = random.nextInt(names.size() - 2) +  1 ;
                getOtherName = names.get(sortOtherName);
                logger.info("Nome pego pra substring: {} ", getOtherName);
                String nameSubstring = getOtherName.substring(0,3);
                logger.info("Adicionando outro nome: {} ", nameSubstring);
                generateLogin.append(nameSubstring);
            }
        } else {
            logger.info("Nome composto por apenas 2 nomes");
            logger.info("Gerando login... ");
            return generatingLoginWithRandomLetters(names);
        }

        return generateLogin.toString().toLowerCase().substring(0,7).trim().replace(" ", "");

    }



}
