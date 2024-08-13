package org.work.notificationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.work.notificationservice.dto.OrderDTO;
import org.work.notificationservice.entity.Notification;
import org.work.notificationservice.repository.NotificationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "orders", groupId = "notification-group")
    public void handleOrderCreated(String orderJson) throws Exception {
        OrderDTO order = objectMapper.readValue(orderJson, OrderDTO.class);
        Notification notification = new Notification();
        notification.setUserId(order.getUserId());
        notification.setMessage("Your order is created.");
        notification.setNotificationType("EMAIL");
        notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findAllByUserId(userId);
    }
}
