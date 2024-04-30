package org.example.dtos;

import lombok.Data;
import org.example.enums.PaymentStatus;

import java.util.UUID;

@Data
public class PaymentResponseDTO {
    private Integer userId;
    private UUID orderId;
    private Double amount;
    private PaymentStatus status;
}
