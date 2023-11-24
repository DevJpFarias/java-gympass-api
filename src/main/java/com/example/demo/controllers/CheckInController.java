package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.checkin.CheckIn;
import com.example.demo.domain.checkin.CheckInRequest;
import com.example.demo.domain.gym.Gym;
import com.example.demo.domain.user.User;
import com.example.demo.dtos.CheckInDTO;
import com.example.demo.services.CheckInService;
import com.example.demo.services.GymService;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

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
  public ResponseEntity<CheckInDTO> createCheckIn(
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

    CheckInDTO checkInDTO = new CheckInDTO(
      checkIn.getId(),
      checkIn.getCreated_at(),
      checkIn.getValidated_at(),
      checkIn.getUser().getId(),
      checkIn.getGym().getId()
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(checkInDTO);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<String> getUserMetrics(
    @PathVariable String userId
  ) throws Exception {
    User user = userService.getUserProfile(userId);

    Number userMetrics = checkInService.getUserMetrics(user);

    Map<String, Number> metricsMap = new HashMap<>();
    metricsMap.put("checkIns", userMetrics);

    ObjectMapper objectMapper = new ObjectMapper();
    String responseJson = objectMapper.writeValueAsString(metricsMap);

    return ResponseEntity.status(HttpStatus.OK).body(responseJson);
  }
}
