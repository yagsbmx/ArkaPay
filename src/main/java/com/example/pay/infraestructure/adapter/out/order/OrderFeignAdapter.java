package com.example.pay.infraestructure.adapter.out.order;

import com.example.pay.domain.ports.out.OrderPort;
import com.example.pay.infraestructure.dto.order.OrderResponseDto;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderFeignAdapter implements OrderPort {

    private final OrderFeignClient client;

    @Override
    public void updateStatus(Long orderId, String status) {
        ResponseEntity<Void> resp = client.updateStatus(orderId, status);
        if (!resp.getStatusCode().is2xxSuccessful()) {
            throw new IllegalStateException("Fallo confirmando orden " + orderId +
                    " con status=" + status + ". HTTP=" + resp.getStatusCode());
        }
    }
    @Override
  public OrderResponseDto getOrder(Long id) {
    ResponseEntity<OrderResponseDto> resp = client.get(id);
    if (!resp.getStatusCode().is2xxSuccessful() || resp.getBody()==null) {
      throw new IllegalStateException("No se pudo obtener orden " + id);
    }
    return resp.getBody();
  }

  @Override
  public void markPaid(Long id) {
    var resp = client.pay(id);
    if (!(resp.getStatusCode().is2xxSuccessful() || resp.getStatusCode()==HttpStatus.NO_CONTENT)) {
      throw new IllegalStateException("No se pudo marcar como pagada " + id);
    }
  }
}
