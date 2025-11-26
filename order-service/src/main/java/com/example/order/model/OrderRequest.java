package com.example.order.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // Lombok annotation that generates getters, setters, toString(), equals(), and hashCode()
public class OrderRequest {

    private String orderId;
    private String userId;
    private String productId;
    private int quantity;
    private double price;
}
