package com.myparty.dto.house;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.HouseConverter;
import com.myparty.dto.local.PersistLocal;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@DataConverterType(HouseConverter.class)
public class PersistHouse implements Serializable {

    private String name;
    private PersistLocal local;
    private List<PersistZone> zones;

}
