package com.example.demo.domain.user;

import jakarta.persistence.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class User implements UserDetails {
  @Id @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  
  private String name;

  @Column(unique = true)
  private String email;
  
  private String password_hash;

  private UserRole role;

  public User (UserRequest userRequest) {
    this.name = userRequest.name();
    this.email = userRequest.email();
    this.password_hash = userRequest.password();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    
  }

  @Override
  public String getPassword() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
    return true;
  }

  @Override
  public boolean isEnabled() {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
    return true;
  }
}
