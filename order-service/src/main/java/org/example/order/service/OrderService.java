package org.example.order.service;


import lombok.AllArgsConstructor;
import org.example.dtos.OrchestratorRequestDTO;
import org.example.dtos.OrderRequestDTO;
import org.example.dtos.OrderResponseDTO;
import org.example.enums.OrderStatus;
import org.example.order.entity.PurchaseOrder;
import org.example.order.mapper.Mapper;
import org.example.order.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.Map;

@Service
@AllArgsConstructor
public class OrderService {

    private Mapper mapper;

    private PurchaseOrderRepository purchaseOrderRepository;

    private Sinks.Many<OrchestratorRequestDTO> sink;

    public Mono<PurchaseOrder> createOrder(OrderRequestDTO orderRequestDTO){
        return this.purchaseOrderRepository.save(mapper.dtoToEntity(orderRequestDTO))
                .doOnNext(e -> orderRequestDTO.setOrderId(e.getId()))
                .doOnNext(e -> this.emitEvent(orderRequestDTO));
    }

    public Flux<OrderResponseDTO> getAll() {
        return this.purchaseOrderRepository.findAll()
                .map(mapper::entityToDto);
    }

    private void emitEvent(OrderRequestDTO orderRequestDTO){
        this.sink.tryEmitNext(mapper.getOrchestratorRequestDTO(orderRequestDTO));
    }


}
