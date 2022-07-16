package com.myparty.dto.house;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.ZoneConverter;
import lombok.Data;

import java.io.Serializable;

@Data
@DataConverterType(ZoneConverter.class)
public class PersistZone implements Serializable {

    private String name;
    private Double size;
    private Long houseId;

}
