package com.example.warehouse.repository;

import com.example.warehouse.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;


public interface CategoryRepository extends JpaRepository<Category,Integer> {

     @Modifying
     @Transactional
     @Query(value = "DELETE FROM category WHERE parrent_category_id = :id", nativeQuery = true)
     int deleteCategoryByParrentCategoryId(Integer id);


}
