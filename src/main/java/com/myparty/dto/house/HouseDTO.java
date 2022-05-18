package com.myparty.dto.house;

import java.util.List;

import lombok.Data;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.HouseConverter;
import com.myparty.dto.DTOType;
import com.myparty.dto.LocalDTO;
import com.myparty.dto.ZoneDTO;

@Data
@DataConverterType(value = HouseConverter.class, dto =true)
public class HouseDTO {

    private Long id;
    private String name;
    private LocalDTO local;
    private List<ZoneDTO> zones;

}
