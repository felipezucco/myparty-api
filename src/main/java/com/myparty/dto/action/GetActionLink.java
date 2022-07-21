package com.myparty.dto.action;


import com.myparty.annotations.DataConverterType;
import com.myparty.converter.action.ActionLinkConverter;
import com.myparty.dto.production.GetProduction;
import com.myparty.dto.promotion.GetPromotion;
import com.myparty.dto.visual.GetVisual;
import lombok.Data;

import java.io.Serializable;

@Data
@DataConverterType(ActionLinkConverter.class)
public class GetActionLink implements Serializable {

    //private GetAction action;
    private GetProduction production;
    private GetVisual visual;
    private GetPromotion promotion;

}
