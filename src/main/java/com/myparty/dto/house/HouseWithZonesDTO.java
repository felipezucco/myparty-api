package com.myparty.dto.house;

import java.util.List;

import lombok.Data;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.HouseConverter;
import com.myparty.dto.ZoneDTO;

@Data
@DataConverterType(value = HouseConverter.class, dto =true)
public class HouseWithZonesDTO {
    
    private List<ZoneDTO> zones;

}
