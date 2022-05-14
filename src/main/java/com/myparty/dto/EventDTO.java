package com.myparty.dto;

import com.myparty.model.Event;
import lombok.Data;
import com.myparty.annotations.DataConverterType;

@Data
@DataConverterType(Event.class)
public class EventDTO {

    private Long id;
    private String name;
    private String date;
    private Long houseId;
    private Long organizationId;

}
