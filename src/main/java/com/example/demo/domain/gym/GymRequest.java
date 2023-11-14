package com.example.demo.domain.gym;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record GymRequest(
  @NotNull @NotEmpty
  String title,

  String description,

  String phone,

  @NotNull
  Double latitude,

  @NotNull
  Double longitude
) {
}
