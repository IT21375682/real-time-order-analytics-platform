package com.example.Analytics.service;

import com.example.Analytics.model.OrderEntity;
import com.example.Analytics.repository.OrderRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class OrderConsumerService {

    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

    public OrderConsumerService(OrderRepository orderRepository, ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.objectMapper = objectMapper;
    }

    // Listen for Kafka messages
    @KafkaListener(topics = "orders-topic", groupId = "analytics-group")
    public void consumeOrderEvent(String message) {
        try {
            // Deserialize the JSON string into OrderEntity
            OrderEntity order = objectMapper.readValue(message, OrderEntity.class);

            // Save the order to the Postgres database
            orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
