package com.myparty.dto.promotion;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.PromotionConverter;
import lombok.Data;

import java.io.Serializable;

@Data
@DataConverterType(PromotionConverter.class)
public class PersistPromotion implements Serializable {

    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private Long eventId;

}
