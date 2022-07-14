package com.myparty.repository;

import com.myparty.model.notification.Notification;
import com.myparty.model.notification.NotificationSent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationSentRepository extends JpaRepository<NotificationSent, Long>{

    @Query("SELECT ns FROM NotificationSent ns INNER JOIN Notification n ON ns.notification.id = n.id WHERE ns.user.id = :id")
    List<NotificationSent> getNotificationSentByUserId(@Param("id") Long id);
}
