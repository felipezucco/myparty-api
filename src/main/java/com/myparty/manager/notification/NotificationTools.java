package com.myparty.manager.notification;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.myparty.interfaces.notification.NotificationListener;
import com.myparty.model.UserProfile;
import com.myparty.model.notification.Notification;
import com.myparty.model.notification.NotificationSent;
import com.myparty.service.UserService;

@Component
public class NotificationTools {

	public static final String POST = "POST";
	public static final String UPDATE = "UPDATE";
	public static final String REMOVE = "REMOVE";

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private UserService userService;

	public Notification buildNotification(String type, NotificationListener listener) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserProfile user = userService.getUserByUsername(username);

		Notification notification = getType(type, listener, user.getId());
		notification.setOrganization(listener.getOrganization());
		notification.setDate(new Date());
		return notification;
	}

	public void buildOrganizationNotificationSent(NotificationListener listener, Notification notification) {
		listener.getOrganization().getOrganizers().forEach(o -> {
			NotificationSent notificationSent = new NotificationSent();
			notificationSent.setUser(o.getUser());
			notificationSent.setNotification(notification);
			notificationSent.setVisualized(false);
			notification.addSent(notificationSent);
		});
	}

	public Notification getType(String type, NotificationListener listener, Long userId) {
		switch (type) {
		case POST:
			return listener.postNotification(userId);
		case UPDATE:
			return listener.postNotification(userId);
		default:
			return listener.postNotification(userId);
		}
	}

}
