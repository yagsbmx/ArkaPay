package com.example.pay.infraestructure.adapter.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pay.domain.model.enums.PaymentMethod;
import com.example.pay.infraestructure.adapter.persistence.entity.PaymentEntity;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {
    
    List<PaymentEntity> findByPaymentMethod(PaymentMethod paymentMethod);


    

}
