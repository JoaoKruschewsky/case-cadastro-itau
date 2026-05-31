package com.example.demo.domain.model.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(name = "cpf", nullable = false)
    private String CPF;

    @Column(name = "documento", nullable = false, unique = true)
    private String documento;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    @OneToOne
    @JoinColumn(name = "login_user")
    private Login login;

    @Embedded
    private Address endereco;



}