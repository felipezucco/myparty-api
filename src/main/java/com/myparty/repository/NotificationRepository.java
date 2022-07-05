package com.myparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myparty.model.notification.Notification;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{

    @Query("SELECT n FROM Notification n INNER JOIN NotificationSent ns ON ns.notification.id = n.id WHERE ns.user.id = :id")
    List<Notification> getNotificationByUserId(@Param("id") Long id);
}
