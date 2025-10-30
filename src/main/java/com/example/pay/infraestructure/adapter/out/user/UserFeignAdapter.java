package com.example.pay.infraestructure.adapter.out.user;

import com.example.pay.domain.ports.out.UserPort;
import com.example.pay.infraestructure.dto.user.UserResponseDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserFeignAdapter implements UserPort {

    private final UserFeignClient client;

    @Override
    public boolean existsById(Long userId) {
        try {
            client.getById(userId); 
            return true;
        } catch (FeignException.NotFound e) {
            return false;
        }
    }

    @Override
    public UserSummary getById(Long userId) {
        UserResponseDto dto = client.getById(userId); 
        UserSummary u = new UserSummary();
        u.setId(dto.getId());
        String fullName = ((dto.getFirstName() != null ? dto.getFirstName() : "") + " " +
                           (dto.getLastName() != null ? dto.getLastName() : "")).trim();
        u.setName(!fullName.isBlank() ? fullName : dto.getUsername());
        u.setEmail(dto.getEmail());
        return u;
    }

    @Override
    public Optional<UserResponseDto> findById(Long id) {
        return Optional.ofNullable(client.getById(id)); 
    }
}
