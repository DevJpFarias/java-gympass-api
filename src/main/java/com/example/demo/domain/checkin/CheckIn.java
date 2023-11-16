package com.example.demo.domain.checkin;

import java.time.LocalDateTime;
import java.util.Set;

import com.example.demo.domain.gym.Gym;
import com.example.demo.domain.user.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;

public class CheckIn {
  @Id @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private LocalDateTime created_at;

  private LocalDateTime validated_at;

  @ManyToMany
  @JoinTable(
    name = "check_in_user_gym",
    joinColumns = @JoinColumn(name = "checkin_id"),
    inverseJoinColumns = {
      @JoinColumn(name = "user_id"),
      @JoinColumn(name = "gym_id")
    }
  )
  private Set<User> users;

  @ManyToMany(mappedBy = "checkIns")
  private Set<Gym> gyms;

  @PrePersist
  protected void onCreate() {
    created_at = LocalDateTime.now();
  }

  public CheckIn (CheckInRequest checkInRequest) {
    this.validated_at = checkInRequest.validated_at();
  }
}
