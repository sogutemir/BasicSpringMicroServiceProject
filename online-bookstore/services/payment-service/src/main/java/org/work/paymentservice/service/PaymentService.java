package org.work.paymentservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.work.paymentservice.dto.OrderDTO;
import org.work.paymentservice.entity.Payment;
import org.work.paymentservice.repository.PaymentRepository;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;

    @KafkaListener(topics = "orders", groupId = "payment-group")
    public void handleOrderCreated(OrderDTO order) {
        Payment payment = new Payment();
        payment.setOrderId(order.getId());
        payment.setPaymentStatus("PENDING");
        payment.setPaymentMethod("CREDIT_CARD");
        paymentRepository.save(payment);

        restTemplate.postForObject("http://order-service/orders/payment", payment, Payment.class);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }
}
