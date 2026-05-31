package com.example.demo.domain.model.entity;


import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "login_user")
@Builder
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "login", unique = true)
    private String login;

}
