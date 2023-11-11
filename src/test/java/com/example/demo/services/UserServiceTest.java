package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRequest;
import com.example.demo.infra.DuplicateEmailException;
import com.example.demo.repositories.UserRepository;

// @SpringJUnitConfig
// @SpringBootTest(webEnvironment = WebEnvironment.NONE)
// @ActiveProfiles("test")
// @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
// @TestPropertySource(locations = "classpath:application-test.properties")
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

  @Test
  public void sameEmailErrorTest() {
    UserRequest userRequest = new UserRequest(
      "Fulano",
      "fulano@mail.com",
      "123456"
    );

    userService.createUser(userRequest);

    try {
      UserRequest duplicateUserRequest = new UserRequest(
        "OutroFulano",
        "fulano@mail.com",
        "654321"
      );
      userService.createUser(duplicateUserRequest);

      fail("Esperava-se uma exceção DuplicateEmailException");
    } catch (DuplicateEmailException error) {
      assertTrue(error.getMessage().contains("O email já está em uso!"), "Deve haver uma violação de restrição de chave única no email");
    }
  }
}
