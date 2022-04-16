package com.example.warehouse.payload;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private Integer category_id;
    private Integer photo_id;
    private String code;
    private Integer measurement_id;
}
