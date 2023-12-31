package com.example.demo.domain.checkin;

import java.time.LocalDateTime;

import com.example.demo.domain.gym.Gym;
import com.example.demo.domain.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="checkIns")
@Entity(name="checkIns")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class CheckIn {
  @Id @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private LocalDateTime created_at;

  private LocalDateTime validated_at;

  @ManyToOne
  @JoinColumn(name = "user_id")
  User user;

  @ManyToOne
  @JoinColumn(name = "gym_id")
  Gym gym;

  @JsonManagedReference
  public User getUser() {
    return user;
  }

  @JsonManagedReference
  public Gym getGym() {
    return gym;
  }
  
  @PrePersist
  protected void onCreate() {
    created_at = LocalDateTime.now();
  }

  public CheckIn (LocalDateTime validated_at, User user, Gym gym) {
    this.validated_at = validated_at;
    this.user = user;
    this.gym = gym;
  }
}
