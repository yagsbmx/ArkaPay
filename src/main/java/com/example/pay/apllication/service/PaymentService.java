package com.example.pay.apllication.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pay.domain.model.Payment;
import com.example.pay.domain.model.enums.PaymentMethod;
import com.example.pay.domain.model.enums.PaymentStatus;
import com.example.pay.domain.ports.in.PaymentUseCase;
import com.example.pay.domain.ports.out.OrderPort;
import com.example.pay.domain.ports.out.PaymentRepositoryPort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService implements PaymentUseCase {

    private static final String ORDER_STATUS_ON_SUCCESS = "CONFIRMED";

    private final PaymentRepositoryPort paymentRepositoryPort;
    private final OrderPort orderPort;

    @Override
    @Transactional
    public Payment processPayment(Payment payment) {
        validatePayment(payment);
        setInitialPaymentState(payment);
        Payment saved = paymentRepositoryPort.save(payment);

        boolean approved = processExternalGateway(saved);

        if (approved) {
            saved.setStatus(PaymentStatus.COMPLETED);
            saved.setUpdatedAt(LocalDateTime.now());
            saved = paymentRepositoryPort.update(saved);
            try {
                orderPort.updateStatus(saved.getOrderId(), ORDER_STATUS_ON_SUCCESS);
            } catch (Exception ex) {
                saved.setStatus(PaymentStatus.FAILED);
                saved.setUpdatedAt(LocalDateTime.now());
                paymentRepositoryPort.update(saved);
                throw new IllegalStateException("No se pudo confirmar la orden en order-service", ex);
            }
            return saved;
        } else {
            saved.setStatus(PaymentStatus.FAILED);
            saved.setUpdatedAt(LocalDateTime.now());
            return paymentRepositoryPort.update(saved);
        }
    }

    private void validatePayment(Payment payment) {
        if (payment == null) throw new IllegalArgumentException("El pago no puede ser nulo.");
        if (isInvalidAmount(payment)) throw new IllegalArgumentException("Monto inválido.");
        if (isBlank(payment.getCurrency())) throw new IllegalArgumentException("Moneda requerida.");
        if (payment.getPaymentMethod() == null) throw new IllegalArgumentException("Método de pago requerido.");
        if (payment.getClientId() == null || payment.getOrderId() == null)
            throw new IllegalArgumentException("Usuario y orden requeridos.");
    }

    private boolean isInvalidAmount(Payment p) {
        return p.getAmount() == null || p.getAmount() <= 0;
    }

    private boolean isBlank(String text) {
        return text == null || text.isBlank();
    }

    private void setInitialPaymentState(Payment payment) {
        payment.setStatus(PaymentStatus.PENDING);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());
    }

    private boolean processExternalGateway(Payment payment) {
        if (payment.getPaymentMethod() == PaymentMethod.EXTERNAL) {
            return payment.getAmount() != null && payment.getAmount() > 0;
        }
        return payment.getAmount() != null && payment.getAmount() > 0;
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pago no encontrado con ID: " + id));
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepositoryPort.findAll();
    }

    @Override
    @Transactional
    public Payment updatePaymentStatus(Long id, PaymentStatus status) {
        Payment existingPayment = getPaymentById(id);
        existingPayment.setStatus(status);
        existingPayment.setUpdatedAt(LocalDateTime.now());
        return paymentRepositoryPort.update(existingPayment);
    }

    @Override
    @Transactional
    public void deletePayment(Long id) {
        getPaymentById(id);
        paymentRepositoryPort.delete(id);
    }

    @Override
    public List<Payment> findByPaymentMethod(String method) {
        return paymentRepositoryPort.findByPaymentMethod(method);
    }
}
