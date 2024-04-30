package org.example.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class PaymentRequestDTO {
    private Integer userId;
    private UUID orderId;
    private Double amount; // product price
}
