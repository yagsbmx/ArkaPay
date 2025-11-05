package com.example.pay.infraestructure.adapter.out.order;

import com.example.pay.infraestructure.config.FeignAuthForwardConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.pay.infraestructure.dto.order.OrderResponseDto;

@FeignClient(
        name = "order-service",
        path = "/api/orders",
        configuration = FeignAuthForwardConfig.class
)
public interface OrderFeignClient {

    @PutMapping("/{id}/status")
    ResponseEntity<Void> updateStatus(@PathVariable("id") Long id,
                                      @RequestParam("status") String status);

    @GetMapping("/{id}")
    ResponseEntity<OrderResponseDto> get(@PathVariable("id") Long id);

    @PostMapping("/{id}/pay")
    ResponseEntity<Void> pay(@PathVariable("id") Long id);
}

