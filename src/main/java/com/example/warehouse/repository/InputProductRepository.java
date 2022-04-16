package com.example.warehouse.repository;

import com.example.warehouse.entity.InputProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {
        boolean existsByProductIdAndExpireDateAndInputId(Integer product_id, Date expireDate, Integer input_id);
}
