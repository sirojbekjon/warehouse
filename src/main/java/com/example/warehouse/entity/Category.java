package com.example.warehouse.entity;

import com.example.warehouse.entity.template.AbsEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Category extends AbsEntity {

    @JsonIgnore
    @ManyToOne
    private Category parrentCategory;


    @JsonProperty(value = "parent_name")
    private String parentName()
    {
        return this.parrentCategory.getName();
    }

    @JsonProperty(value = "parent_id")
    private Integer parentID()
    {
        return this.parrentCategory.getId();
    }
}
