package com.myparty.dto;

import com.myparty.annotations.DTO;
import com.myparty.model.Local;
import lombok.Data;

@Data
@DTO(Local.class)
public class LocalDTO {

    private Long id;
    private String city;
    private String state;
    private String aisle;
    private String code;
    private String block;
    private Integer number;
    private String complement;
    private Double coordenateX;
    private Double coordenateY;

}
