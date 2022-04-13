package com.myparty.dto;

import com.myparty.annotations.DTO;
import com.myparty.model.Organizer;
import lombok.Data;

@Data
@DTO(Organizer.class)
public class OrganizerDTO {

    private Long id;
    private UserWithoutPasswordDTO user;
    private Long roleId;
    private Long organizationId;

}
