package com.example.pay.infraestructure.dto;

import java.time.LocalDateTime;

import com.example.pay.domain.model.enums.PaymentMethod;
import com.example.pay.domain.model.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDto {

    private Long id;
    private Double amount;
    private String currency;
    private String description;
    private PaymentStatus status;
    private Long userId;
    private Long orderId;
    private PaymentMethod paymentMethod;
    private String transactionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
