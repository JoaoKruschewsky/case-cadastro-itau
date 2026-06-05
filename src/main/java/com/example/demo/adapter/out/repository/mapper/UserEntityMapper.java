package com.example.demo.adapter.out.repository.mapper;

import com.example.demo.adapter.out.repository.entity.UserEntity;
import com.example.demo.domain.model.Address;
import com.example.demo.domain.model.User;

public class UserEntityMapper {

    public static UserEntity toEntity(User user) {

        UserEntity.Address address = UserEntity.Address.builder()
                .logradouro(user.getEndereco().getLogradouro())
                .bairro(user.getEndereco().getBairro())
                .estado(user.getEndereco().getEstado())
                .uf(user.getEndereco().getUf())
                .build();

        return UserEntity.builder()
                .nomeCompleto(user.getNomeCompleto())
                .CPF(user.getCpf())
                .email(user.getEmail())
                .dataNascimento(user.getDataNascimento())
                .cep(user.getCep())
                .loginName(user.getLoginName())
                .endereco(address)
                .build();
    }

    public static User toDomain(UserEntity entity) {

        Address address = new Address(
                entity.getEndereco().getLogradouro(),
                entity.getEndereco().getBairro(),
                entity.getEndereco().getEstado(),
                entity.getEndereco().getUf()
        );

        return new User(
                entity.getId(),
                entity.getNomeCompleto(),
                entity.getCPF(),
                entity.getEmail(),
                entity.getDataNascimento(),
                entity.getCep(),
                entity.getLoginName(),
                address
        );
    }
}

