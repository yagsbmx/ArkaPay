package com.example.pay.infraestructure.adapter.out.user;

import com.example.pay.infraestructure.config.FeignAuthForwardConfig;
import com.example.pay.infraestructure.dto.user.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service",
        path = "/api/users",
        configuration = FeignAuthForwardConfig.class
)
public interface UserFeignClient {

    @GetMapping("/{id}")
    UserResponseDto getById(@PathVariable("id") Long id);
}
