package com.myparty.dto.organization;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.organization.OrganizationConverter;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@DataConverterType(OrganizationConverter.class)
public class PersistOrganization implements Serializable {

    private String name;
    private Boolean favorite;
    private List<PersistOrganizer> organizers;

}
