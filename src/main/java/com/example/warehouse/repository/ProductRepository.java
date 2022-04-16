package com.example.warehouse.repository;

import com.example.warehouse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM category WHERE parrent_category_id = :id", nativeQuery = true)
    int deleteCategoryByParrentCategoryId(Integer id);
    boolean existsByNameAndCodeAndCategoryId(String name, String code, Integer category_id);
}
