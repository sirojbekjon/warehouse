package com.example.warehouse.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InputProductDto {
    private Integer product_id;
    private Double amount;
    private Integer price;
    private Timestamp expire_date;
    private Integer input_id;
}
