package com.example.demo.adapter.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressDTO (@NotBlank String logradouro, @NotBlank String bairro, @NotBlank String estado, @NotBlank String uf){
}
