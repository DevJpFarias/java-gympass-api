package com.example.demo.domain.gym;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="gyms")
@Entity(name="gym")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Gym {
  @Id @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String title;
  private String description;
  private String phone;
  private Double latitude;
  private Double longitude;

  public Gym (GymRequest gymRequest) {
    this.title = gymRequest.title();
    this.description = gymRequest.description();
    this.phone = gymRequest.phone();
    this.latitude = gymRequest.latitude();
    this.longitude = gymRequest.longitude();
  }
}
