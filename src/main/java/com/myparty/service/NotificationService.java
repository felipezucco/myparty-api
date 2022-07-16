package com.myparty.service;

import com.myparty.model.notification.NotificationSent;
import com.myparty.repository.NotificationSentRepository;
import org.springframework.stereotype.Service;

import com.myparty.model.notification.Notification;
import com.myparty.repository.NotificationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

	private NotificationRepository notificationRepository;
	private NotificationSentRepository notificationSentRepository;

	public NotificationService(NotificationRepository notificationRepository, NotificationSentRepository notificationSentRepository) {
		this.notificationRepository = notificationRepository;
		this.notificationSentRepository = notificationSentRepository;
	}

	public Notification persistNotification(Notification notification) {
		return notificationRepository.save(notification);
	}

	public List<Notification> getNotificationsByUserId(Long id) {
		return notificationRepository.getNotificationByUserId(id);
	}

	public List<NotificationSent> getNotificationSentForUserId(Long id) {
		return notificationSentRepository.getNotificationSentByUserId(id);
	}

	public NotificationSent persistNotificationSent(NotificationSent notificationSent) {
		return notificationSentRepository.save(notificationSent);
	}

	public boolean setNotificationSeen(Long id) {
		Optional<NotificationSent> notificationSent = notificationSentRepository.findById(id);
		if (notificationSent.isPresent()) {
			notificationSent.get().setVisualized(true);
			persistNotificationSent(notificationSent.get());
			return true;
		}

		return false;
	}
}
