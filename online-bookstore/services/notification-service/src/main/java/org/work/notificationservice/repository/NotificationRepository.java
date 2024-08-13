package org.work.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.notificationservice.entity.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByUserId(Long userId);
}