package com.myparty.dto;

import com.myparty.annotations.DTO;
import com.myparty.model.Event;
import lombok.Data;

@Data
@DTO(Event.class)
public class EventDTO {

    private Long id;
    private String name;
    private String date;
    private Long houseId;
    private Long organizationId;

}
