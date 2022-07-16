package com.myparty.converter.notification;

import java.util.Locale;
import java.util.stream.Collectors;

import com.myparty.converter.ConverterComponent;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.notification.NotificationSent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.myparty.dto.GetNotification;
import com.myparty.interfaces.OldDataConverter;
import com.myparty.manager.notification.NotificationTools;
import com.myparty.service.UserService;
import com.myparty.utils.DateFormat;

@Component
public class NotificationConverter extends ConverterComponent implements DataConverterInterface<NotificationSent> {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private NotificationTools notificationTools;

	@Autowired
	private UserService userService;

	@Override
	public <T> T convert(NotificationSent entity, T destinationClass) {
		GetNotification dto = new GetNotification();

		dto.setDate(DateFormat.format(entity.getNotification().getDate()));
		dto.setId(entity.getId());
		dto.setOrganization(entity.getNotification().getOrganization().getName());
		dto.setUser(entity.getUser().getUsername());
		dto.setVisualized(entity.isVisualized());

		dto.setMessage(messageSource.getMessage(entity.getNotification().getNotificationType().getCode(), null, Locale.getDefault()));

		dto.setAttributes(entity.getNotification().getAttributes().stream().map(t -> {
			if (t.getReferenceColumn().equals("username")) {
				String nameById = userService.getNameById(Long.parseLong(t.getValue()));
				t.setValue(nameById);
			}
			return t.getValue();
		}).collect(Collectors.toList()));

		return (T) dto;
	}

	@Override
	public NotificationSent revert(Object o) {
		return null;
	}
}
