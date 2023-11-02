package com.example.demo.domain.gym;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record GymRequest(
  @NotBlank
  String title,

  @NotBlank @Null
  String description,

  @NotBlank @Null
  String phone,

  @NotBlank @NotNull
  Number latitude,

  @NotBlank @NotNull
  Number longitude
) {
}
