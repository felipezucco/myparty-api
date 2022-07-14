package com.myparty.dto.event;

import com.myparty.dto.organization.GetOrganization;
import lombok.Data;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.EventConverter;
import com.myparty.dto.house.GetHouse;

import java.io.Serializable;

@Data
@DataConverterType(EventConverter.class)
public class GetEvent extends PersistEvent implements Serializable {

    private Long id;
    private GetHouse house;
    private GetOrganization organization;

}
