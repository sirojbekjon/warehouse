package com.example.warehouse.payload;

import lombok.Data;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String code;
    private String password;
    private Integer[] warehouses_ids;
}
