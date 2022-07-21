package com.myparty.dto.action;


import com.myparty.annotations.DataConverterType;
import com.myparty.converter.action.ActionConverter;
import com.myparty.dto.organization.GetOrganization;
import com.myparty.dto.organization.GetOrganizer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@DataConverterType(ActionConverter.class)
public class GetAction implements Serializable {

    private Long id;
    private String name;
    private String startDate;
    private String endDate;
    private List<GetOrganizer> organizers;
    private GetActionLink actionLink;

}
