package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRequest;
import com.example.demo.domain.user.UsersRepository;

@Service
public class CreateUserService {
  @Autowired
  UsersRepository repository;

  public void execute(UserRequest data) {
    User newUser = new User(data);
    
    repository.save(newUser);
  }
}
