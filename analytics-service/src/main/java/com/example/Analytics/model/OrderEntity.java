package com.example.Analytics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Instant;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderEntity {

    @Id
    private String orderId;
    private String userId;
    private String productId;
    private int quantity;
    private double price;
    private Instant createdAt;
}
