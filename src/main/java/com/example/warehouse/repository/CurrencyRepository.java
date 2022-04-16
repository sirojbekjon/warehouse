package com.example.warehouse.repository;

import com.example.warehouse.entity.Currency;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {

     boolean existsByName(String name);
     boolean existsById(Integer id);
}
