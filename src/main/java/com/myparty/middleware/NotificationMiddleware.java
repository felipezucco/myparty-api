package com.myparty.middleware;

import com.myparty.dto.NotificationDTO;
import com.myparty.model.notification.NotificationSent;
import com.myparty.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationMiddleware extends RootMiddleware {

    @Autowired
    private NotificationService notificationService;

    public List<NotificationDTO> getNotificationsByUserId(Long id) {
        List<NotificationSent> notifications = notificationService.getNotificationSentForUserId(id);
        return convert(notifications);
    }

    public boolean setNotificationSeen(Long id) {
        return notificationService.setNotificationSeen(id);
    }


}
