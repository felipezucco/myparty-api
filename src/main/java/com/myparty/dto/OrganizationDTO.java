package com.myparty.dto;

import com.myparty.annotations.DTO;
import com.myparty.model.Organization;
import java.util.List;

import lombok.Data;

@Data
@DTO(Organization.class)
public class OrganizationDTO {

    private Long id;
    private String name;
    private Long accountId;
    private List<OrganizerDTO> organizers;   

}
