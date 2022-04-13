package com.myparty.dto;

import com.myparty.annotations.DTO;
import com.myparty.model.House;
import java.util.List;

import lombok.Data;

@Data
@DTO(House.class)
public class HouseDTO {

    private Long id;
    private String name;
    private LocalDTO local;
    private List<ZoneDTO> zones;

}
