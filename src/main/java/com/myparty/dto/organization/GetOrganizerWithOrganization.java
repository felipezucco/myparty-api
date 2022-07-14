package com.myparty.dto.organization;

import lombok.Data;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.organization.OrganizerConverter;

import java.io.Serializable;

@Data
@DataConverterType(OrganizerConverter.class)
public class GetOrganizerWithOrganization extends GetOrganizer implements Serializable {

    private GetOrganization organization;

}
