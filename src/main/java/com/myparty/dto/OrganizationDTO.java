package com.myparty.dto;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.OrganizationConverter;
import com.myparty.dto.organizer.OrganizerWithoutOrganizationDTO;
import java.util.List;

import lombok.Data;

@Data
@DataConverterType(value = OrganizationConverter.class, dto = true)
public class OrganizationDTO {

    private Long id;
    private String name;
    private List<OrganizerWithoutOrganizationDTO> organizers;   

}
