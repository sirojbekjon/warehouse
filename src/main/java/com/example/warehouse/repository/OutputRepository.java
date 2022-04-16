package com.example.warehouse.repository;

import com.example.warehouse.entity.Output;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutputRepository extends JpaRepository<Output,Integer> {
    boolean existsByFactureNumberAndCode(String factureNumber, String code);
}
