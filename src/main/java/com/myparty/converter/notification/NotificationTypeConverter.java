package com.myparty.converter.notification;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.myparty.enums.notification.NotificationTypeEnum;

@Converter
public class NotificationTypeConverter implements AttributeConverter<NotificationTypeEnum, String> {

	@Override
	public String convertToDatabaseColumn(NotificationTypeEnum attribute) {
		return attribute.name();
	}

	@Override
	public NotificationTypeEnum convertToEntityAttribute(String dbData) {
		return Stream.of(NotificationTypeEnum.values()).filter(type -> type.name().equals(dbData)).findFirst()
				.orElse(null);
	}

}
