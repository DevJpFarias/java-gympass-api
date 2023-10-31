package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UsersRepository;

@Service
public class ListAllUsersService {
  @Autowired
  private UsersRepository repository;

  public List<User> execute() {
    var users = repository.findAll();

    return users;
  }
}
