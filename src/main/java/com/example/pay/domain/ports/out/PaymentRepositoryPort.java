package com.example.pay.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.example.pay.domain.model.Payment;

public interface PaymentRepositoryPort {

    Payment save(Payment payment);
    Optional<Payment> findById(Long id);
    List<Payment> findAll();
    Payment update(Payment payment);
    void delete(Long id);
    List<Payment> findByPaymentMethod(String method);


}
