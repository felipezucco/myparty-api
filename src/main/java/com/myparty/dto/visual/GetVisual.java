package com.myparty.dto.visual;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.VisualConverter;
import com.myparty.dto.event.GetEvent;
import com.myparty.dto.financial.GetFinancial;
import com.myparty.dto.financial.PersistFinancial;
import lombok.Data;

import java.io.Serializable;

@Data
@DataConverterType(VisualConverter.class)
public class GetVisual implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String type;
    private String channel;
    private String releaseDate;
    private GetFinancial financial;
    private GetEvent event;

}
