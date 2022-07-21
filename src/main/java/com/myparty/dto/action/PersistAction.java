package com.myparty.dto.action;


import com.myparty.annotations.DataConverterType;
import com.myparty.converter.action.ActionConverter;
import lombok.Data;

import java.io.Serializable;

@Data
@DataConverterType(ActionConverter.class)
public class PersistAction implements Serializable {

    private String name;
    private String startDate;
    private String endDate;
    private Long eventId;
    private Long[] organizersId;
    private PersistActionLink actionLink;

}
