package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.gym.Gym;

public interface GymRepository extends JpaRepository<Gym, String> {
  List<Gym> findByTitleContaining(String title);
}
