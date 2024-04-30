package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dtos.PaymentRequestDTO;
import org.example.dtos.PaymentResponseDTO;
import org.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/debit")
    public PaymentResponseDTO debit(@RequestBody PaymentRequestDTO requestDTO){
        log.info("debit client account");
        return this.service.debit(requestDTO);
    }

    @PostMapping("/credit")
    public void credit(@RequestBody PaymentRequestDTO requestDTO){
        this.service.credit(requestDTO);
    }

    @GetMapping("userBalance")
    public Map<Integer, Double> get() {
        return service.getUserBalanceMap();
    }

}
