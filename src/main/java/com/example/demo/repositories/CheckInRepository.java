package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.checkin.CheckIn;

public interface CheckInRepository extends JpaRepository<CheckIn, String> {}
