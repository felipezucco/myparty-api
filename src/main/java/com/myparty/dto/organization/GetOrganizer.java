package com.myparty.dto.organization;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.organization.OrganizerConverter;
import com.myparty.dto.user.GetUser;
import lombok.Data;

import java.io.Serializable;

@Data
@DataConverterType(OrganizerConverter.class)
public class GetOrganizer implements Serializable {

    private Long id;
    private GetUser user;
    private Integer role;

}
