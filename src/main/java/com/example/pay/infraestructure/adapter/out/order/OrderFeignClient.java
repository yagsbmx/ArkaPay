package com.example.pay.infraestructure.adapter.out.order;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.pay.infraestructure.dto.order.OrderResponseDto;

@FeignClient(name = "order-service")
public interface OrderFeignClient {

@PutMapping("/api/orders/{id}/status")
    ResponseEntity<Void> updateStatus(@PathVariable("id") Long id,
                                      @RequestParam("status") String status);

@GetMapping("/api/orders/{id}")
  ResponseEntity<OrderResponseDto> get(@PathVariable("id") Long id);
  

@PostMapping("/api/orders/{id}/pay")
  ResponseEntity<Void> pay(@PathVariable("id") Long id);
}
