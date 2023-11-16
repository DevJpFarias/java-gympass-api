package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.checkin.CheckIn;
import com.example.demo.domain.checkin.CheckInRequest;
import com.example.demo.repositories.CheckInRepository;

public class CheckInService {
  private final CheckInRepository checkInRepository;

  @Autowired
  public CheckInService(CheckInRepository repository) {
    this.checkInRepository = repository;
  }

  public CheckIn createCheckIn(CheckInRequest data) {
    CheckIn checkIn = new CheckIn(data);

    checkInRepository.save(checkIn);

    return checkIn;
  }
}
