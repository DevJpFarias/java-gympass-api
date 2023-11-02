package com.example.demo.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

public record UserRequest(
  @NotBlank
  String name,

  @NotBlank @Email
  String email,

  @NotBlank @Min(6)
  String password
) {
}