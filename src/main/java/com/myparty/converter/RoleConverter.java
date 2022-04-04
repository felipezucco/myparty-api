package com.myparty.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.myparty.enums.RoleEnum;


@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<RoleEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(RoleEnum attribute) {
		if (attribute == null) return null;
		
		return attribute.getId();
	}

	@Override
	public RoleEnum convertToEntityAttribute(Integer dbData) {
		if (dbData == null) return null;
		
		return Stream.of(RoleEnum.values()).filter(role -> role.getId().equals(dbData)).findFirst().orElseThrow(IllegalArgumentException::new);
	}
	
}