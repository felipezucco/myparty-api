package com.myparty.converter.notification;

import java.util.Locale;
import java.util.stream.Collectors;

import com.myparty.model.notification.NotificationSent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.myparty.dto.NotificationDTO;
import com.myparty.interfaces.OldDataConverter;
import com.myparty.manager.notification.NotificationTools;
import com.myparty.service.UserService;
import com.myparty.utils.DateFormat;

@Component
public class NotificationSentConverterOld implements OldDataConverter<NotificationSent, NotificationDTO> {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private NotificationTools notificationTools;

	@Autowired
	private UserService userService;

	@Override
	public NotificationDTO convert(NotificationSent entity) {
		NotificationDTO dto = new NotificationDTO();

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

		return dto;
	}

	@Override
	public NotificationSent revert(NotificationDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
