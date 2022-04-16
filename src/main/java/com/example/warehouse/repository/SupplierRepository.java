package com.example.warehouse.repository;

import com.example.warehouse.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    boolean existsByName(String name);
    boolean existsById(Integer id);
}
