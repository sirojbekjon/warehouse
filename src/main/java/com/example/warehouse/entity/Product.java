package com.example.warehouse.entity;


import com.example.warehouse.entity.template.AbsEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Entity

public class Product extends AbsEntity {

    @JsonIgnore
    @ManyToOne
    private Category category;

    @JsonIgnore
    @OneToOne
    private Attachment photo;

    private String code;

    @JsonIgnore
    @ManyToOne
    private Measurement measurement;

    @JsonProperty(value = "category_name")
    private String categoryName(){return this.category.getName();}
//
//    @JsonProperty(value = "category_id")
//    private Integer categoryId()
//    {
//        return this.category.getId();
//    }

    @JsonProperty(value = "measurement_name")
    private String measurementName(){return this.measurement.getName();}

//    @JsonProperty(value = "measurement_id")
//    private Integer measurementId()
//    {
//        return this.measurement.getId();
//    }
}
