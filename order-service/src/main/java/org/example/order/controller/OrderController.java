package org.example.order.controller;


import org.example.dtos.OrderRequestDTO;
import org.example.dtos.OrderResponseDTO;
import org.example.order.entity.PurchaseOrder;
import org.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/create")
    public Mono<PurchaseOrder> createOrder(@RequestBody Mono<OrderRequestDTO> mono){
        //map to apply a function on each element sent by Mono ( each OrderRequestDTO ) , flat : to construct one Mono from multiple Monos
        // Mono<Mono<PurchaseOrder>> mono1= mono.map(this.service::createOrder);
        //Mono<PurchaseOrder> mono2 = mono.flatMap(this.service::createOrder);
        return mono
                .flatMap(this.service::createOrder);
    }

    @GetMapping("/all")
    public Flux<OrderResponseDTO> getOrders(){
        return this.service.getAll();
    }

}
