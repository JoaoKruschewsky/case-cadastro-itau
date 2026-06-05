package com.example.demo.adapter.out.repository;

import com.example.demo.adapter.out.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByCPF(String cpf);
    Optional<UserEntity> findByLoginName(String login);
}
