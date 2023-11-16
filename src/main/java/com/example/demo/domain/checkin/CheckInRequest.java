package com.example.demo.domain.checkin;

import java.time.LocalDateTime;

import com.example.demo.domain.gym.Gym;
import com.example.demo.domain.user.User;

public record CheckInRequest(
  LocalDateTime validated_at,

  User user,

  Gym gym
) {
}
