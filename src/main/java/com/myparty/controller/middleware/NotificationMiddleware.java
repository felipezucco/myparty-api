package com.myparty.controller.middleware;

import com.myparty.dto.NotificationDTO;
import com.myparty.dto.UserWithoutPasswordDTO;
import com.myparty.model.notification.Notification;
import com.myparty.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationMiddleware extends RootMiddleware {

    @Autowired
    private NotificationService notificationService;

    public List<NotificationDTO> getNotificationsByUserId(Long id) {
        List<Notification> notifications = notificationService.getNotificationsByUserId(id);
        return convert(notifications);
    }


}
