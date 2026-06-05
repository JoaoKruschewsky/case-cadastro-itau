package com.example.demo.domain.model;

public class Address {

    private String logradouro;
    private String bairro;
    private String estado;
    private String uf;

    public Address(String logradouro, String bairro, String estado, String uf) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.estado = estado;
        this.uf = uf;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getEstado() {
        return estado;
    }

    public String getUf() {
        return uf;
    }
}
