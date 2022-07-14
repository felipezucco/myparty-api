package com.myparty.dto.organization;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.organization.OrganizationConverter;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@DataConverterType(OrganizationConverter.class)
public class GetOrganizationWithOrganizers extends GetOrganizerWithOrganization implements Serializable {

    private List<GetOrganizer> organizers;

}
