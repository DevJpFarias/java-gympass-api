package com.example.demo.infra.exceptions;

public class GymNotFoundException extends RuntimeException {
  public GymNotFoundException() {
    super("Academia não encontrada!");
  }
}
