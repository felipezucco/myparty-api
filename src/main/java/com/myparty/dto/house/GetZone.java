package com.myparty.dto.house;

import lombok.Data;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.ZoneConverter;

import java.io.Serializable;

@Data
@DataConverterType(ZoneConverter.class)
public class GetZone extends PersistZone implements Serializable {

    private Long id;

}
