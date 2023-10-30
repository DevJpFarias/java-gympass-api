package com.example.demo.user.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  @GetMapping
  public String hello() {
    return "Hello, Spring Boot!";
  }
}