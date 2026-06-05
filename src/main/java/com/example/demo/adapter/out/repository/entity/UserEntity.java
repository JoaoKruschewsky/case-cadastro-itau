package com.example.demo.adapter.out.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(unique = true)
    private String CPF;
    @Column(unique = true)
    private String email;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private String cep;
    @Column(name = "login_name", unique = true)
    private String loginName;

    @Embedded
    private Address endereco;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Embeddable
    public static class Address {

        @Column(name = "logradouro")
        private String logradouro;

        @Column(name = "bairro")
        private String bairro;

        @Column(name = "estado")
        private String estado;

        @Column(name = "uf", length = 2)
        private String uf;
    }
}