package com.myparty.converter;

import com.myparty.dto.financial.GetFinancial;
import com.myparty.dto.financial.PersistFinancial;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.Financial;
import org.springframework.stereotype.Component;

@Component
public class FinancialConverter extends ConverterComponent implements DataConverterInterface<Financial> {

    @Override
    public <T> T convert(Financial entity, T destinationClass) {
        destinationClass = getDefault(destinationClass, GetFinancial.class);

        if (destinationClass instanceof GetFinancial) {
            GetFinancial gf = (GetFinancial) destinationClass;
            gf.setId(entity.getId());
            gf.setValue(entity.getValue());
        }

        return destinationClass;
    }

    @Override
    public Financial revert(Object o) {
        Financial financial = new Financial();

        if (o instanceof PersistFinancial) {
            PersistFinancial pf = (PersistFinancial) o;
            financial.setValue(pf.getValue());
        }
        return financial;
    }
}
