package com.example.pay.infraestructure.dto.order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
  private Long id;
  private Long userId;
  private List<OrderItemDto> items;
  private BigDecimal total;
  private String status;
  private Instant createdAt;

  @Data
  public static class OrderItemDto {
    private Long productId;
    private Integer qty;
    private BigDecimal price;
    private BigDecimal lineTotal;
  }
}