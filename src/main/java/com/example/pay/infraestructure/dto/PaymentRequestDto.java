package com.example.pay.infraestructure.dto;

import java.time.LocalDateTime;

import com.example.pay.domain.model.enums.PaymentMethod;
import com.example.pay.domain.model.enums.PaymentStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {

    private Long id;

    @NotNull(message = "Amount is required and cannot be null")
    @Positive(message = "Amount must be greater than zero")
    private Double amount;

    @NotBlank(message = "Currency is required and cannot be blank")
    private String currency;

    @NotBlank(message = "Description is required and cannot be blank")
    private String description;

    @NotNull(message = "Status is required and cannot be null")
    private PaymentStatus status;

    @NotNull(message = "UserId is required and cannot be null")
    private Long userId;

    @NotNull(message = "OrderId is required and cannot be null")
    private Long orderId;

    @NotNull(message = "PaymentMethod is required and cannot be null")
    private PaymentMethod paymentMethod;

    private String transactionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}



