package com.example.pay.infraestructure.adapter.persistence.entity;

import java.time.LocalDateTime;

import com.example.pay.domain.model.enums.PaymentMethod;
import com.example.pay.domain.model.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double amount;
    private String currency;
    private String description;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private Long clientId;
    private Long orderId;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String transactionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
