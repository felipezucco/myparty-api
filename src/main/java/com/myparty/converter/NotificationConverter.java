package com.myparty.converter;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.myparty.dto.NotificationDTO;
import com.myparty.interfaces.DataConverter;
import com.myparty.manager.notification.NotificationTools;
import com.myparty.model.notification.Notification;
import com.myparty.model.notification.NotificationAttribute;
import com.myparty.service.UserService;
import com.myparty.utils.DateFormat;

@Component
public class NotificationConverter implements DataConverter<Notification, NotificationDTO> {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private NotificationTools notificationTools;

	@Autowired
	private UserService userService;

	@Override
	public NotificationDTO convert(Notification entity) {
		NotificationDTO dto = new NotificationDTO();

		dto.setDate(DateFormat.format(entity.getDate()));
		dto.setId(entity.getId());

		dto.setMessage(messageSource.getMessage(entity.getNotificationType().getCode(), null, Locale.getDefault()));
		dto.setOrganization(entity.getOrganization().getName());
		dto.setUser(entity.getUser().getName());

		List<String> collect = entity.getAttributes().stream().map(t -> {
			if (t.getReferenceColumn().equals("username")) {
				String nameById = userService.getNameById(Long.parseLong(t.getValue()));
				t.setValue(nameById);
			}
			return t.getValue();
		}).collect(Collectors.toList());

		dto.setAttributes(collect);
		return dto;
	}

	@Override
	public Notification revert(NotificationDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
