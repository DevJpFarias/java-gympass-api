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
  UserRepository repository;

  public User createUser(UserRequest data) {
    User newUser = new User(data);
    
    repository.save(newUser);

    return newUser;
  }

  public List<User> getAllUsers() {
    var users = repository.findAll();

    return users;
  }

  public Optional<User> findUserById(String id) {
    var user = repository.findById(id);

    return user;
  }
}
