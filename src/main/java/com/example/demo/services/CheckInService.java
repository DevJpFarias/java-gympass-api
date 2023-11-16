package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repositories.CheckInRepository;

public class CheckInService {
  private final CheckInRepository checkInRepository;

  @Autowired
  public CheckInService(CheckInRepository repository) {
    this.checkInRepository = repository;
  }
}
