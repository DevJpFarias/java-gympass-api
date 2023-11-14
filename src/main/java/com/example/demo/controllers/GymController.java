package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.gym.Gym;
import com.example.demo.domain.gym.GymRequest;
import com.example.demo.services.GymService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("gyms")
public class GymController {
  @Autowired
  GymService gymService;

  @PostMapping
  public ResponseEntity<Gym> createGym(@RequestBody @Valid GymRequest data) {
    Gym gym = gymService.createGym(data);

    return ResponseEntity.status(HttpStatus.CREATED).body(gym);
  }
}
