package com.example.pay.domain.ports.in;

import java.util.List;

import com.example.pay.domain.model.Payment;
import com.example.pay.domain.model.enums.PaymentStatus;

public interface PaymentUseCase {

    Payment processPayment(Payment payment);
    Payment getPaymentById(Long id);
    List<Payment> getAllPayments();
    Payment updatePaymentStatus(Long id, PaymentStatus status);
    void deletePayment(Long id);
    List<Payment> findByPaymentMethod(String method);


}
