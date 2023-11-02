package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.user.User;

public interface UserRepository extends JpaRepository<User, String> {
  List<User> findAll();
}