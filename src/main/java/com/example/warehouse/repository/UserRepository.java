package com.example.warehouse.repository;

import com.example.warehouse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByFirstNameAndLastNameAndPhoneNumber
            (String firstName, String lastName, String phoneNumber);
}
