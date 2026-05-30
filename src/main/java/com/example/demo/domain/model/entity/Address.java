package com.example.demo.domain.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Column(name = "logradoura")
    private String logradouro;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "estado")
    private String estado;
    @Column(name = "uf", length = 2)
    private String uf;

}
