package com.myparty.dto.action;


import com.myparty.annotations.DataConverterType;
import com.myparty.converter.action.ActionConverter;
import com.myparty.converter.action.ActionLinkConverter;
import lombok.Data;

import java.io.Serializable;

@Data
@DataConverterType(ActionLinkConverter.class)
public class PersistActionLink implements Serializable {

    private Long actionId;
    private Long productionId;
    private Long visualId;
    private Long promotionId;

}
