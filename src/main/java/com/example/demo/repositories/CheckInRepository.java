package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.checkin.CheckIn;
import com.example.demo.domain.user.User;

public interface CheckInRepository extends JpaRepository<CheckIn, String> {
  Number countByUser(User user);
  List<CheckIn> findAllByUser(User user);
}
