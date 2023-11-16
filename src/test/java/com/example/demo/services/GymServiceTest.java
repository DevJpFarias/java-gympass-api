package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.domain.gym.Gym;
import com.example.demo.domain.gym.GymRequest;
import com.example.demo.repositories.GymRepository;

public class GymServiceTest {
  @Mock
  private GymRepository repository;

  private GymService gymService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    gymService = new GymService(repository);
  }

  @Test
  public void createGymTest() {
    GymRequest gymRequest = new GymRequest(
      "Academia Teste",
      null,
      null,
      -22.7266211,
      -43.5126098
    );

    Gym expectedGym = new Gym(gymRequest);

    when(repository.save(any(Gym.class))).thenReturn(expectedGym);

    Gym createdGym = gymService.createGym(gymRequest);

    assertEquals(expectedGym, createdGym, "Deve ser possível criar uma academia");
  }
}
