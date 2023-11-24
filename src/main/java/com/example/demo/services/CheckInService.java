package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.checkin.CheckIn;
import com.example.demo.domain.gym.Gym;
import com.example.demo.domain.user.User;
import com.example.demo.repositories.CheckInRepository;

@Service
public class CheckInService {
  private final CheckInRepository checkInRepository;

  @Autowired
  public CheckInService(CheckInRepository repository) {
    this.checkInRepository = repository;
  }

  public CheckIn createCheckIn(
    User user,
    Gym gym
  ) {
    LocalDateTime validated_at = null;

    CheckIn checkIn = new CheckIn(
      validated_at,
      user,
      gym
    );

    checkInRepository.save(checkIn);

    return checkIn;
  }

  public Number getUserMetrics(User user) {
    return checkInRepository.countByUser(user);
  }

  public List<CheckIn> fetchUserCheckIns(User user) {
    return checkInRepository.findAllByUser(user);
  }
}
