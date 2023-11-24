package com.example.demo.domain.gym;

import java.util.Set;

import com.example.demo.domain.checkin.CheckIn;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

  @OneToMany(mappedBy = "gym")
  Set<CheckIn> checkIns;

  @JsonBackReference
  public Set<CheckIn> getCheckIns() {
    return checkIns;
  }

  // @ManyToMany
  // @JoinTable(
  //   name = "checkin_user_gym",
  //   joinColumns = @JoinColumn(name = "gym_id"),
  //   inverseJoinColumns = @JoinColumn(name = "user_id")
  // )
  // private Set<User> users;

  public Gym (GymRequest gymRequest) {
    this.title = gymRequest.title();
    this.description = gymRequest.description();
    this.phone = gymRequest.phone();
    this.latitude = gymRequest.latitude();
    this.longitude = gymRequest.longitude();
  }
}
