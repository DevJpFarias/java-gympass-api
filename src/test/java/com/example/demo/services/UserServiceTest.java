package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRequest;
import com.example.demo.infra.exceptions.UserNotFoundException;
// import com.example.demo.infra.DuplicateEmailException;
import com.example.demo.repositories.UserRepository;

@ActiveProfiles("test")
public class UserServiceTest {
  @Mock
  private UserRepository repository;

  @Mock
  private UserService userService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    userService = new UserService(repository);
  }

  @Test
  @DisplayName("Should create a new user sucessfully")
  public void createUserTest() {
    UserRequest userRequest = new UserRequest("Fulano", "fulano@mail.com", "123456");

    User createdUser = userService.createUser(userRequest);

    assertNotNull(createdUser);
  }

  @Test
  @DisplayName("Should hash user password")
  public void passwordEncryptionTest() {
    String password = "123456";

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    UserRequest userRequest = new UserRequest("Fulano", "fulano@mail.com", password);

    User createdUser = userService.createUser(userRequest);

    String userHashedPassword = createdUser.getPassword_hash();
    
    assertTrue(!password.equals(userHashedPassword));
    assertTrue(passwordEncoder.matches(password, userHashedPassword));
  }

  @Test
  @DisplayName("Should be able to get user profile")
  public void getUserProfile() throws Exception {
    UserRequest userRequest = new UserRequest("Fulano", "fulano@mail.com", "123456");

    User createdUser = userService.createUser(userRequest);

    String userId  = createdUser.getId();

    assertNotNull(userId);

    User user = userService.getUserProfile(userId);

    String userName = user.getName();
    String userEmail = user.getEmail();

    assertEquals("Fulano", userName);
    assertEquals("fulano@mail.com", userEmail);
  }

  @Test
  @DisplayName("Should be able to get user profile")
  public void getUserProfileError() throws Exception {
    assertThrows(UserNotFoundException.class, () -> {
      userService.getUserProfile("Inexistent id");
    });
  }

  // @Test
  // @DisplayName("Should not be able to create user with existent email")
  // public void sameEmailErrorTest() {
  //   UserRequest userRequest = new UserRequest("Fulano", "fulano@mail.com", "123456");

  //   userService.createUser(userRequest);

  //   assertThrows(DuplicateEmailException.class, () -> {
  //     UserRequest duplicateUserRequest = new UserRequest("OutroFulano", "fulano@mail.com", "654321");
  //     userService.createUser(duplicateUserRequest);
  //   });
  // }

  // @Test
  // @DisplayName("Should return true when used with existent email")
  // public void getEmailAndReturnTrue() {
  //   UserRequest userRequest = new UserRequest("Fulano", "fulano@mail.com", "123456");

  //   User newUser = new User(userRequest);

  //   repository.save(newUser);

  //   assertEquals(1, repository.count());

  //   boolean emailExists = userService.checkEmailAlreadyUsed("fulano@mail.com");

  //   assertTrue(emailExists);
  // }

  // @Test
  // @DisplayName("Should return false when used with inexistent email")
  // public void getEmailAndReturnFalse() {
  //   boolean emailExists = userService.checkEmailAlreadyUsed("fulano@mail.com");

  //   assertFalse(emailExists);
  // }
}
