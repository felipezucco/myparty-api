package com.myparty.dto.event;


import com.myparty.annotations.DataConverterType;
import com.myparty.converter.EventConverter;
import lombok.Data;

import java.io.Serializable;

@Data
@DataConverterType(EventConverter.class)
public class PersistEvent implements Serializable {

    private String name;
    private String date;
    private Long houseId;
}
