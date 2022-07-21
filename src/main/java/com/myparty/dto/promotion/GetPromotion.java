package com.myparty.dto.promotion;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.PromotionConverter;
import com.myparty.dto.event.GetEvent;
import lombok.Data;

import java.io.Serializable;

@Data
@DataConverterType(PromotionConverter.class)
public class GetPromotion implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private GetEvent event;

}
