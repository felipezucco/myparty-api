package com.myparty.dto;

import com.myparty.annotations.DTO;
import com.myparty.model.Zone;
import lombok.Data;

@Data
@DTO(Zone.class)
public class ZoneDTO {

    private Long id;
    private String name;
    private Double size;
    private Long houseId;

}
