package com.example.warehouse.entity;


import com.example.warehouse.entity.template.AbsEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Currency extends AbsEntity {
}
