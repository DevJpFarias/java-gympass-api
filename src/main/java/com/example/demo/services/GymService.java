package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.gym.Gym;
import com.example.demo.domain.gym.GymRequest;
import com.example.demo.repositories.GymsRepository;

@Service
public class GymService {
  private final GymsRepository gymsRepository;

  @Autowired
  public GymService(GymsRepository repository) {
    this.gymsRepository = repository;
  }

  public Gym createGym(GymRequest data) {
    Gym newGym = new Gym(data);

    gymsRepository.save(newGym);

    return newGym;
  }
}
