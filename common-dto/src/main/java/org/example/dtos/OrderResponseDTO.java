package org.example.dtos;

import lombok.Data;
import org.example.enums.OrderStatus;

import java.util.UUID;

@Data
public class OrderResponseDTO {

    private UUID orderId;
    private Integer userId;
    private Integer productId;
    private Double amount;
    private OrderStatus status;

}
