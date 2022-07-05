package com.myparty.interfaces.notification;

import com.myparty.model.notification.Notification;
import com.myparty.model.organization.Organization;

public interface NotificationListener {

	Notification postNotification(Long userId);
	void afterUpdate();
	void afterDelete();
	Organization getOrganization();

}
