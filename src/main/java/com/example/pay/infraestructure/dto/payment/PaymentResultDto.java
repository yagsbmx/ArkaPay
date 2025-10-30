package com.example.pay.infraestructure.dto.payment;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResultDto {
  private String status;
  private Long orderId;
  private Instant paidAt;
}