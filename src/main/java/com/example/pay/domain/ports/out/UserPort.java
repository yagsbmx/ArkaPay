package com.example.pay.domain.ports.out;

import java.util.Optional;

import com.example.pay.infraestructure.adapter.out.user.UserSummary;
import com.example.pay.infraestructure.dto.user.UserResponseDto;

public interface UserPort {
    boolean existsById(Long userId);
    UserSummary getById(Long userId);
    Optional<UserResponseDto> findById(Long id);
}
