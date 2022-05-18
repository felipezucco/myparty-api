package com.myparty.dto;

import com.myparty.dto.house.HouseDTO;
import lombok.Data;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.ZoneConverter;

@Data
@DataConverterType(value = ZoneConverter.class, dto = true)
public class ZoneDTO {

    private Long id;
    private String name;
    private Double size;

}
