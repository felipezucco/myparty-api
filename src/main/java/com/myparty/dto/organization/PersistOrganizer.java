package com.myparty.dto.organization;

import com.myparty.converter.organization.OrganizerConverter;
import lombok.Data;
import com.myparty.annotations.DataConverterType;

import java.io.Serializable;

@Data
@DataConverterType(OrganizerConverter.class)
public class PersistOrganizer implements Serializable {

    private Long userId;
    private Integer role;
    private Long organizationId;

}
