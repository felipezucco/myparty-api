package com.myparty.model.production;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "production_type")
public class ProductionType {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

}
