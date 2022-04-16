package com.example.warehouse.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OutputProductDto {
    private Integer product_id;
    private Double amount;
    private Integer price;
    private Integer output_id;
}
