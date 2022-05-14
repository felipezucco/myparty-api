package com.myparty.dto;

import com.myparty.model.Zone;
import lombok.Data;
import com.myparty.annotations.DataConverterType;

@Data
@DataConverterType(Zone.class)
public class ZoneDTO {

    private Long id;
    private String name;
    private Double size;
    private HouseDTO house;

}
