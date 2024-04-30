package org.example.dtos;

import lombok.Data;
import org.example.enums.InventoryStatus;

import java.util.UUID;

@Data
public class InventoryResponseDTO {

    private UUID orderId;
    private Integer userId;
    private Integer productId;
    private InventoryStatus status;

}
