package com.myparty.dto.organization;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.organization.OrganizationConverter;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
@DataConverterType(OrganizationConverter.class)
public class GetOrganization implements Serializable {

    private Long id;
    private String name;

}
