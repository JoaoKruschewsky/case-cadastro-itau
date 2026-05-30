package com.example.demo.adapter.out.repository;

import com.example.demo.domain.model.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
}
