package com.example.pay.infraestructure.dto.payment;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentRequestDto {
  private Long orderId;
  private String method;
  private BigDecimal amount;
}
