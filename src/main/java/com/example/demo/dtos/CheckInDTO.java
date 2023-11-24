package com.example.demo.dtos;

import java.time.LocalDateTime;

public record CheckInDTO(
  String id,
  LocalDateTime created_at,
  LocalDateTime validated_at,
  String user_id,
  String gym_id
) {}
