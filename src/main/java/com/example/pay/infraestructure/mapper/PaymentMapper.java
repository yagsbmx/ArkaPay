package com.example.pay.infraestructure.mapper;

import org.springframework.stereotype.Component;

import com.example.pay.domain.model.Payment;
import com.example.pay.infraestructure.adapter.persistence.entity.PaymentEntity;
import com.example.pay.infraestructure.dto.PaymentRequestDto;
import com.example.pay.infraestructure.dto.PaymentResponseDto;

@Component
public class PaymentMapper {

    public Payment toDomain(PaymentEntity entity) {
        return new Payment(
            entity.getId(),
            entity.getAmount(),
            entity.getCurrency(),
            entity.getDescription(),
            entity.getStatus(),
            entity.getClientId(),
            entity.getOrderId(),
            entity.getPaymentMethod(),
            entity.getTransactionId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }

    public PaymentEntity toEntity(Payment payment) {
        return new PaymentEntity(
            payment.getId(),    
            payment.getAmount(),
            payment.getCurrency(),
            payment.getDescription(),
            payment.getStatus(),
            payment.getClientId(),
            payment.getOrderId(),
            payment.getPaymentMethod(),
            payment.getTransactionId(),
            payment.getCreatedAt(),
            payment.getUpdatedAt()
        );
            
    }

    public PaymentResponseDto toResponseDto(Payment payment) {
        return new PaymentResponseDto(
            payment.getId(),
            payment.getAmount(),
            payment.getCurrency(),
            payment.getDescription(),
            payment.getStatus(),
            payment.getClientId(),
            payment.getOrderId(),
            payment.getPaymentMethod(),
            payment.getTransactionId(),
            payment.getCreatedAt(),
            payment.getUpdatedAt()
        );
    }

    public Payment requestToDomain(PaymentRequestDto request) {
        return new Payment(
            null,
            request.getAmount(),
            request.getCurrency(),
            request.getDescription(),
            null,
            request.getUserId(),
            request.getOrderId(),
            request.getPaymentMethod(),
            null,
            null,
            null
        );
    }

}
