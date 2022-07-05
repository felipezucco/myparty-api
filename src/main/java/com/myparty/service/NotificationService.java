package com.myparty.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.myparty.dto.NotificationDTO;
import com.myparty.model.notification.Notification;
import com.myparty.repository.NotificationRepository;

import java.util.List;

@Service
public class NotificationService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private NotificationRepository notificationRepository;

	public void sendMessage(String queueName, NotificationDTO messageDTO) {
		String json = new Gson().toJson(messageDTO);
		rabbitTemplate.convertAndSend(queueName, json);
	}

	public Notification persistNotification(Notification notification) {
		return notificationRepository.save(notification);
	}

	public List<Notification> getNotificationsByUserId(Long id) {
		return notificationRepository.getNotificationByUserId(id);
	}

}
