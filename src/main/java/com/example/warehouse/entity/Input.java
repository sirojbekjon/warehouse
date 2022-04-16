package com.example.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.objects.annotations.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Input {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Timestamp date;

    @JsonIgnore
    @ManyToOne
    private Warehouse warehouse;
    @JsonIgnore
    @ManyToOne
    private Supplier supplier;
    @JsonIgnore
    @ManyToOne
    private Currency currency;

    private String factureNumber;

    @Column (unique = true, nullable = false)
    private String code;

    @JsonProperty(value = "warehouse_name")
    private String warehouseName(){
        return this.warehouse.getName();
    }

    @JsonProperty(value = "supplier_name")
    private String supplierName(){
        return this.supplier.getName();
    }

    @JsonProperty(value = "currency_name")
    private String currencyName(){
        return this.currency.getName();
    }
}
