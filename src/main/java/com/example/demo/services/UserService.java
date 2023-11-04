package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRequest;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
  @Autowired
  UserRepository userRepository;

  public User createUser(UserRequest data) {
    User newUser = new User(data);
    
    userRepository.save(newUser);

    return newUser;
  }

  public List<User> getAllUsers() {
    var users = userRepository.findAll();

    return users;
  }

  public Optional<User> findUserById(String id) {
    var user = userRepository.findById(id);

    return user;
  }

  public User updateUser(User user) {
    return userRepository.save(user);
  }
}
