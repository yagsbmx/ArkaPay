package com.example.pay.infraestructure.dto.user;

import java.time.Instant;
import lombok.Data;

@Data
public class UserResponseDto {
  private Long id;
  private String username;
  private String email;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String address;
  private boolean active;
  private String country;
  private String city;
  private String role;
  private Instant createdAt;
}
