package com.myparty.dto.organizer;

import com.myparty.dto.UserWithoutPasswordDTO;
import com.myparty.dto.OrganizationDTO;
import lombok.Data;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.OrganizerConverter;

@Data
@DataConverterType(value = OrganizerConverter.class, dto = true)
public class OrganizerDTO {

    private Long id;
    private UserWithoutPasswordDTO user;
    private Integer role;
    private OrganizationDTO organization;

}
