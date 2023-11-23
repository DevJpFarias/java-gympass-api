package com.example.demo.domain.checkin;

public record CheckInRequest(
  String user_id,

  String gym_id
) {
}
