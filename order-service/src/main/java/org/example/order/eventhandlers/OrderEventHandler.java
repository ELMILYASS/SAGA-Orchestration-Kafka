package org.example.order.eventhandlers;


import org.example.dtos.OrchestratorRequestDTO;
import org.example.dtos.OrchestratorResponseDTO;
import org.example.order.service.OrderEventUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class OrderEventHandler {

    @Autowired
    private Flux<OrchestratorRequestDTO> flux;

    @Autowired
    private OrderEventUpdateService service;

    @Bean
    public Supplier<Flux<OrchestratorRequestDTO>> supplier(){
        return () -> flux;
    };

    @Bean
    public Consumer<Flux<OrchestratorResponseDTO>> consumer(){
        return orchestratorResponseDTOFlux -> orchestratorResponseDTOFlux
                .doOnNext(c -> System.out.println("Consuming :: " + c))
                .flatMap(responseDTO -> this.service.updateOrder(responseDTO))
                .subscribe();
    };

}
