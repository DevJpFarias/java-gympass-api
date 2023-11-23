package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.checkin.CheckIn;
import com.example.demo.domain.checkin.CheckInRequest;
import com.example.demo.domain.gym.Gym;
import com.example.demo.domain.user.User;
import com.example.demo.services.CheckInService;
import com.example.demo.services.GymService;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/checkins")
public class CheckInController {
  @Autowired
  CheckInService checkInService;

  @Autowired
  UserService userService;

  @Autowired
  GymService gymService;

  @PostMapping
  public ResponseEntity<CheckIn> createCheckIn(
    @RequestBody
    @Valid
    CheckInRequest data
  ) throws Exception {
    User user = userService.getUserProfile(data.user_id());

    Gym gym = gymService.findGymById(data.gym_id());

    CheckIn checkIn = checkInService.createCheckIn(
      user,
      gym
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(checkIn);
  }
}
