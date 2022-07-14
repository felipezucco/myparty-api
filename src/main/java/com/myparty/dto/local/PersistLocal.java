package com.myparty.dto.local;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.LocalConverter;
import lombok.Data;

import java.io.Serializable;

@Data
@DataConverterType(LocalConverter.class)
public class PersistLocal implements Serializable {

    private String city;
    private String state;
    private String aisle;
    private String code;
    private String block;
    private Integer number;
    private String complement;
    private Double coordenateX;
    private Double coordenateY;
    private Long organizationId;

}
