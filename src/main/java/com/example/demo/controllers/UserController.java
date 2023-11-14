package com.example.demo.controllers;

import java.util.List;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRequest;
import com.example.demo.infra.exceptions.UserNotFoundException;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
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
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    var users = userService.getAllUsers();

    return ResponseEntity.status(HttpStatus.OK).body(users);
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody @Valid UserRequest data) {
    User user = userService.createUser(data);

    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @GetMapping(":id")
  public ResponseEntity getUserProfile(String id) throws Exception {
    try {
      User user = userService.getUserProfile(id);

      return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    } catch (UserNotFoundException error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
  }
}