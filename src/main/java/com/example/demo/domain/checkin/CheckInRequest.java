package com.example.demo.domain.checkin;

import java.time.LocalDateTime;

public record CheckInRequest(
  LocalDateTime validated_at
) {
}
