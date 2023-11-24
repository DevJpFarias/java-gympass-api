package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRequest;
import com.example.demo.dtos.UserDTO;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    var users = userService.getAllUsers();

    List<UserDTO> userDTOList = new ArrayList<>();

    for (User user : users) {
      UserDTO userDTO = new UserDTO(
        user.getId(),
        user.getName(),
        user.getEmail()
      );
      userDTOList.add(userDTO);
    }

    return ResponseEntity.status(HttpStatus.OK).body(userDTOList);
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody @Valid UserRequest data) {
    User user = userService.createUser(data);

    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserProfile(@PathVariable String id) throws Exception {
    User user = userService.getUserProfile(id);

    UserDTO userDTO = new UserDTO(
      user.getId(),
      user.getName(),
      user.getEmail())
    ;

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDTO);
  }
}