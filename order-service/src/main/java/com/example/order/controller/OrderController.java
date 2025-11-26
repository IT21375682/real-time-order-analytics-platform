package com.example.order.controller;

import com.example.order.model.OrderRequest;
import com.example.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest order) {
        orderService.sendOrder(order);  // Sends the order to Kafka
        return ResponseEntity.ok("Order event published");
    }
}
