package com.example.pay.domain.model;

import com.example.pay.domain.model.enums.PaymentStatus;

import java.time.LocalDateTime;

import com.example.pay.domain.model.enums.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Payment {

    private Long id;
    private Double amount;
    private String currency;
    private String description;
    private PaymentStatus status;
    private Long clientId;
    private Long orderId;
    private PaymentMethod paymentMethod;
    private String transactionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
