package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.gym.Gym;
import com.example.demo.domain.gym.GymRequest;
import com.example.demo.repositories.GymRepository;

@Service
public class GymService {
  private final GymRepository gymsRepository;

  @Autowired
  public GymService(GymRepository repository) {
    this.gymsRepository = repository;
  }

  public Gym createGym(GymRequest data) {
    Gym newGym = new Gym(data);

    gymsRepository.save(newGym);

    return newGym;
  }

  public List<Gym> searchGyms(String title) {
    return gymsRepository.findByTitleContaining(title);
  }
}
