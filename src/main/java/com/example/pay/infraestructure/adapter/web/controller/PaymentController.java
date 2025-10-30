package com.example.pay.infraestructure.adapter.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pay.domain.model.Payment;
import com.example.pay.domain.model.enums.PaymentStatus;
import com.example.pay.domain.ports.in.PaymentUseCase;
import com.example.pay.infraestructure.dto.PaymentRequestDto;
import com.example.pay.infraestructure.dto.PaymentResponseDto;
import com.example.pay.infraestructure.mapper.PaymentMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentUseCase useCase;
    private final PaymentMapper mapper;


    /**@PostMapping("/create")
    public ResponseEntity<Payment> processPayment(@RequestBody Payment payment) {
        Payment created = useCase.processPayment(payment);
        return new ResponseEntity<>(created, HttpStatus.CREATED);**/

    @PostMapping("/create")
    public ResponseEntity<PaymentResponseDto> processPayment(@RequestBody PaymentRequestDto dto) {
        Payment created = useCase.processPayment(mapper.requestToDomain(dto));
        return new ResponseEntity<>(mapper.toResponseDto(created), HttpStatus.CREATED);
    }
      
    
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = useCase.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }
    
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = useCase.getAllPayments();
        return ResponseEntity.ok(payments);
    }
    
    @GetMapping("/method/{method}")
    public ResponseEntity<List<Payment>> findByPaymentMethod(@PathVariable String method) {
        List<Payment> payments = useCase.findByPaymentMethod(method);
        return ResponseEntity.ok(payments);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PaymentResponseDto> updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam PaymentStatus status) {
        Payment updated = useCase.updatePaymentStatus(id, status);
        return ResponseEntity.ok(mapper.toResponseDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        useCase.deletePayment(id);
        return ResponseEntity.noContent().build();
    }    
    

}
