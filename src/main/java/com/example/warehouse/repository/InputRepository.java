package com.example.warehouse.repository;

import com.example.warehouse.entity.Input;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface InputRepository extends JpaRepository<Input,Integer> {
    boolean existsByFactureNumberAndCode(String factureNumber, String code);
}
