package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRequest;
import com.example.demo.repositories.UserRepository;

public class UserServiceTest {
  @Mock
  private UserRepository repository;

  private UserService userService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    userService = new UserService(repository);
  }

  @Test
  public void createUserTest() {
    UserRequest userRequest = new UserRequest("Fulano", "fulano@mail.com", "123456");

    User expectedUser = new User(userRequest);

    when(repository.save(any(User.class))).thenReturn(expectedUser);

    User createdUser = userService.createUser(userRequest);

    assertEquals(expectedUser, createdUser, "Deve ser possível se cadastrar.");
  }

  @Test
  public void passwordEncryptionTest() {
    String password = "123456";

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    UserRequest userRequest = new UserRequest("Fulano", "fulano@mail.com", password);

    User createdUser = userService.createUser(userRequest);

    String userHashedPassword = createdUser.getPassword_hash();
    
    assertTrue(!password.equals(userHashedPassword));
    assertTrue(passwordEncoder.matches(password, userHashedPassword));
  }
}
