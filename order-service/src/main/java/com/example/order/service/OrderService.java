package com.example.order.service;

import com.example.order.model.OrderRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class OrderService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "orders-topic";
    private final ObjectMapper objectMapper;

    public OrderService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendOrder(OrderRequest order) {
        try {
            // Serialize the order to JSON string
            String payload = objectMapper.writeValueAsString(order);

            // Send the JSON payload to Kafka
            kafkaTemplate.send(TOPIC, order.getOrderId(), payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
