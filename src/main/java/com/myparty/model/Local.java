package com.myparty.model;

import com.myparty.dto.LocalDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import com.myparty.annotations.DataConverterType;

@Data
@Entity
@DataConverterType(LocalDTO.class)
public class Local {

    @Id
    @GenericGenerator(strategy = "uuid", name = "system-uuid")
    @GeneratedValue(generator = "uuid")
    private Long id;
    private String city;

    @Column(length = 2)
    private String state;
    private String aisle;
    private String code;
    private String block;
    private String complement;
    private Integer number;
    private Double coordenateX;
    private Double coordenateY;

}
