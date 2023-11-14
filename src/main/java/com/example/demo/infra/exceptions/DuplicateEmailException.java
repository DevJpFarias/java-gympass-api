package com.example.demo.infra.exceptions;

public class DuplicateEmailException extends RuntimeException {
  public DuplicateEmailException() {
    super("O email já está em uso!");
  }
}
