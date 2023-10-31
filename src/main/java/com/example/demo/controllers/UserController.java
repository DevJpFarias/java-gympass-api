package com.example.demo.controllers;

import java.util.List;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRequest;
import com.example.demo.services.CreateUserService;
import com.example.demo.services.ListAllUsersService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private ListAllUsersService listAllUsersService;

  @Autowired
  private CreateUserService createUserService;

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    var users = listAllUsersService.execute();

    return ResponseEntity.ok(users);
  }

  @PostMapping
  public ResponseEntity<String> createUser(@RequestBody @Valid UserRequest data) {
    createUserService.execute(data);

    return ResponseEntity.ok().build();
  }
}