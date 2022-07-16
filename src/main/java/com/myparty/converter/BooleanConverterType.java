package com.myparty.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter
public class BooleanConverterType implements AttributeConverter<Boolean, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Boolean attribute) {
        if (attribute != null) {
            return attribute ? 1 : 0;
        }
        return 0;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer dbData) {
        return dbData == 1;
    }
}
