package com.myparty.model;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.FinancialConverter;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "financial")
@DataConverterType(value = FinancialConverter.class)
public class Financial {

    @Id
    @GeneratedValue
    private Long id;
    private Double value;

}
