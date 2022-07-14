package com.myparty.dto.house;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.HouseConverter;
import com.myparty.dto.local.GetLocal;

@Data
@DataConverterType(HouseConverter.class)
public class GetHouse extends PersistHouse implements Serializable {

    private Long id;
    private GetLocal local;
    private List<GetZone> zones;

}
