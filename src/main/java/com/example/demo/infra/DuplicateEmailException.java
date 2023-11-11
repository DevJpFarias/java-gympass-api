package com.example.demo.infra;

public class DuplicateEmailException extends RuntimeException {
  public DuplicateEmailException() {
    super("O email já está em uso!");
  }
}
