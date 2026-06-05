package com.example.demo.domain.model;



import java.time.LocalDate;

public class User {

    private Long id;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    private String cep;
    private String loginName;
    private Address endereco;

    public User(
            Long id,
            String nomeCompleto,
            String cpf,
            String email,
            LocalDate dataNascimento,
            String cep,
            String loginName,
            Address endereco) {

        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cep = cep;
        this.loginName = loginName;
        this.endereco = endereco;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getCep() {
        return cep;
    }

    public String getLoginName() {
        return loginName;
    }

    public Address getEndereco() {
        return endereco;
    }


}