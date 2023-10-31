package com.example.demo.controllers;

import java.util.List;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UsersRepository;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UsersRepository repository;

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    var users = repository.findAll();

    return ResponseEntity.ok(users);
  }
}