package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.gym.Gym;

public interface GymsRepository extends JpaRepository<Gym, String> {}
