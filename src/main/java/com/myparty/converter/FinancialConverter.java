package com.myparty.converter;

import com.myparty.dto.FinancialDTO;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.interfaces.OldDataConverter;
import com.myparty.model.Financial;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class FinancialConverter extends ConverterComponent implements DataConverterInterface<Financial> {

    @Override
    public <T> T convert(Financial entity, T destinationClass) {
        FinancialDTO dto = new FinancialDTO();
        dto.setId(entity.getId());
        dto.setValue(entity.getValue());

        return (T) dto;
    }

    @Override
    public Financial revert(Object o) {
        Financial financial = new Financial();

        if (o instanceof FinancialDTO) {
            FinancialDTO f = (FinancialDTO) o;
            financial.setId(f.getId());
            financial.setValue(f.getValue());
        }
        return financial;
    }
}
