package com.example.demo.services;

import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRequest;
// import com.example.demo.infra.DuplicateEmailException;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository repository) {
    this.userRepository = repository;
  }

  public User createUser(UserRequest data) {
    // Optional<User> user = userRepository.findOneByEmail(data.email());

    // boolean emailAlreadyExists = user.isPresent();

    // if (emailAlreadyExists == true) {
    //   throw new DuplicateEmailException();
    // }

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashPassword = passwordEncoder.encode(data.password());

    UserRequest newData = new UserRequest(
      data.name(),
      data.email(),
      hashPassword
    );

    User newUser = new User(newData);
    userRepository.save(newUser);

    return newUser;
  }

  public List<User> getAllUsers() {
    var users = userRepository.findAll();

    return users;
  }

  public User getUserProfile(String id) throws Exception {
    return userRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado!"));
  }

  public User updateUser(User user) {
    return userRepository.save(user);
  }

  // public boolean checkEmailAlreadyUsed(String email) {
  //   Optional<User> user = userRepository.findOneByEmail(email);

  //   boolean emailAlreadyUsed = user.isPresent();

  //   return emailAlreadyUsed;
  // }
}
