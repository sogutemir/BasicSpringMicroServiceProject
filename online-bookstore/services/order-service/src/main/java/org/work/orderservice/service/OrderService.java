package org.work.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.work.orderservice.dto.OrderDTO;
import org.work.orderservice.entity.Order;
import org.work.orderservice.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public OrderService(KafkaTemplate<String, String> kafkaTemplate, OrderRepository orderRepository, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderRepository = orderRepository;
        this.objectMapper = objectMapper;
    }

    public Order createOrder(Order order) throws Exception {
        Order savedOrder = orderRepository.save(order);
        OrderDTO orderDTO = new OrderDTO(savedOrder.getId(), savedOrder.getUserId(), savedOrder.getBookId(), savedOrder.getQuantity(), savedOrder.getStatus());
        String orderJson = objectMapper.writeValueAsString(orderDTO);
        kafkaTemplate.send("orders", orderJson);
        return savedOrder;
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
