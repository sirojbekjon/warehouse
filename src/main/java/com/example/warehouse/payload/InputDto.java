package com.example.warehouse.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InputDto {
    private Timestamp date;
    private Integer id;
    private Integer warehouse_id;
    private Integer supplier_id;
    private Integer currency_id;
    private String facture_number;
    private String code;

}
