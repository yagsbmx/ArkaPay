package com.example.pay.infraestructure.adapter.out.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.example.pay.infraestructure.dto.user.UserResponseDto;

@FeignClient(name = "user-service") 
public interface UserFeignClient {

   @GetMapping("/api/users/{id}")
    UserResponseDto getById(@PathVariable("id") Long id);

    
}