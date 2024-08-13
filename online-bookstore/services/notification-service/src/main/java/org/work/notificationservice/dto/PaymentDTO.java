package org.work.notificationservice.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    private Long id;
    private Long orderId;
    private String paymentStatus;
    private String paymentMethod;
    private Long userId;
}