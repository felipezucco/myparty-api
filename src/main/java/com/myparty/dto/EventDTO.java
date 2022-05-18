package com.myparty.dto;

import lombok.Data;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.EventConverter;
import com.myparty.dto.house.HouseDTO;

@Data
@DataConverterType(value = EventConverter.class, dto = true)
public class EventDTO {

    private Long id;
    private String name;
    private String date;
    private HouseDTO house;
    private OrganizationDTO organization;

}
