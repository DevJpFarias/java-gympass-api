package com.example.demo.domain.user;

import jakarta.persistence.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Table(name="users")
@Entity(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
  @Id @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  
  private String name;

  @Column(unique = true)
  private String email;
  
  private String password_hash;

  public User (UserRequest userRequest) {
    this.name = userRequest.name();
    this.email = userRequest.email();
    this.password_hash = userRequest.password();
  }
}
