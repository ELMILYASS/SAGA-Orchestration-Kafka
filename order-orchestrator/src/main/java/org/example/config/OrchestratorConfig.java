package org.example.config;

import org.example.dtos.OrchestratorRequestDTO;
import org.example.dtos.OrchestratorResponseDTO;
import org.example.servcie.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Configuration
public class OrchestratorConfig {
    private final OrchestratorService orchestratorService;

    public OrchestratorConfig(OrchestratorService orchestratorService) {
        this.orchestratorService = orchestratorService;
    }

    @Bean
    public Function<Flux<OrchestratorRequestDTO>, Flux<OrchestratorResponseDTO>> processor(){

        return orchestratorRequestDTOFlux -> orchestratorRequestDTOFlux
                .flatMap(orchestratorRequestDTO -> this.orchestratorService.orderProduct(orchestratorRequestDTO))
                .doOnNext(dto -> System.out.println("Status : " + dto.getStatus()));
    }
}
