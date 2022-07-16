package com.myparty.dto.house;

import com.myparty.model.house.House;
import lombok.Data;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.ZoneConverter;

import java.io.Serializable;

@Data
@DataConverterType(ZoneConverter.class)
public class GetZone implements Serializable {

    private Long id;
    private String name;
    private Double size;

}
