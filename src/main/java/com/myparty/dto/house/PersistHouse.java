package com.myparty.dto.house;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.HouseConverter;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@DataConverterType(HouseConverter.class)
public class PersistHouse implements Serializable {

    private String name;
    private Long localId;
    private List<GetZone> zones;

}
