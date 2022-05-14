package com.myparty.dto;

import com.myparty.model.House;
import java.util.List;

import lombok.Data;
import com.myparty.annotations.DataConverterType;

@Data
@DataConverterType(House.class)
public class HouseDTO {

    private Long id;
    private String name;
    private LocalDTO local;
    private List<ZoneDTO> zones;

}
