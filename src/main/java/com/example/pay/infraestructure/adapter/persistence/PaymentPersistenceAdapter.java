package com.example.pay.infraestructure.adapter.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.pay.domain.model.Payment;
import com.example.pay.domain.model.enums.PaymentMethod;
import com.example.pay.domain.ports.out.PaymentRepositoryPort;
import com.example.pay.infraestructure.adapter.persistence.repository.PaymentJpaRepository;
import com.example.pay.infraestructure.mapper.PaymentMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentPersistenceAdapter implements PaymentRepositoryPort {

    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public Payment save(Payment payment) {
        var entity = paymentMapper.toEntity(payment);
        var savedEntity = paymentJpaRepository.save(entity);
        return paymentMapper.toDomain(savedEntity);
    }

      @Override
    public Optional<Payment> findById(Long id) {
        return paymentJpaRepository.findById(id)
                .map(paymentMapper::toDomain);
    }

    @Override
    public List<Payment> findAll() {
        return paymentJpaRepository.findAll()
                .stream()
                .map(paymentMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Payment update(Payment payment) {
        if (!paymentJpaRepository.existsById(payment.getId())) {
            throw new RuntimeException("Payment not found with id: " + payment.getId());
        }
        var entity = paymentMapper.toEntity(payment);
        var updatedEntity = paymentJpaRepository.save(entity);
        return paymentMapper.toDomain(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        if (!paymentJpaRepository.existsById(id)) {
            throw new RuntimeException("Payment not found with id: " + id);
        }
        paymentJpaRepository.deleteById(id);
    }

    @Override
    public List<Payment> findByPaymentMethod(String method) {
        PaymentMethod enumMethod = PaymentMethod.valueOf(method.toUpperCase());
        return paymentJpaRepository.findByPaymentMethod(enumMethod)
                .stream()
                .map(paymentMapper::toDomain)
                .collect(Collectors.toList());
    }

}
