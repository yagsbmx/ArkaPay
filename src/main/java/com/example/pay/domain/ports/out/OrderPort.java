package com.example.pay.domain.ports.out;

import com.example.pay.infraestructure.dto.order.OrderResponseDto;

public interface OrderPort {
    void updateStatus(Long orderId, String status);
    OrderResponseDto getOrder(Long id);
    void markPaid(Long id);
}

