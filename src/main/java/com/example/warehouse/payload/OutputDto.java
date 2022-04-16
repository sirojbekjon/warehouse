package com.example.warehouse.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OutputDto {
    private Timestamp date;
    private Integer id;
    private Integer warehouse_id;
    private Integer currency_id;
    private String facture_number;
    private String code;
    private Integer client_id;


}
