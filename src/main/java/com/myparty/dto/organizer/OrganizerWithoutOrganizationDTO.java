package com.myparty.dto.organizer;

import com.myparty.dto.UserWithoutPasswordDTO;
import lombok.Data;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.OrganizerWithoutOrganizationConverter;

@Data
@DataConverterType(value = OrganizerWithoutOrganizationConverter.class, dto = true)
public class OrganizerWithoutOrganizationDTO {

    private Long id;
    private UserWithoutPasswordDTO user;
    private Integer role;

}
