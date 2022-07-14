package com.myparty.dto.local;

import com.myparty.dto.organization.GetOrganization;
import lombok.Data;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.LocalConverter;

import java.io.Serializable;

@Data
@DataConverterType(LocalConverter.class)
public class GetLocal extends PersistLocal implements Serializable {

    private Long id;
    private GetOrganization organization;

}
