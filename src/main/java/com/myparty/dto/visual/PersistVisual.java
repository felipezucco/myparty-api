package com.myparty.dto.visual;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.VisualConverter;
import com.myparty.dto.financial.PersistFinancial;
import lombok.Data;

import java.io.Serializable;

@Data
@DataConverterType(VisualConverter.class)
public class PersistVisual implements Serializable {

    private String name;
    private String description;
    private String type;
    private String channel;
    private String releaseDate;
    private PersistFinancial financial;
    private Long eventId;

}
