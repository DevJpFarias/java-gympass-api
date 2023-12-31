package com.example.demo.domain.user;

import jakarta.persistence.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.domain.checkin.CheckIn;
import com.fasterxml.jackson.annotation.JsonBackReference;

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

  @OneToMany(mappedBy = "user")
  Set<CheckIn> checkIns;

  @JsonBackReference
  public Set<CheckIn> getCheckIns() {
    return checkIns;
  }

  // @ManyToMany
  // @JoinTable(
  //   name = "checkin_user_gym",
  //   joinColumns = @JoinColumn(name = "user_id"),
  //   inverseJoinColumns = @JoinColumn(name = "gym_id")
  // )
  // private Set<Gym> gyms;

  // @ManyToMany(mappedBy = "users")
  // private Set<CheckIn> checkIns;

  public User (UserRequest userRequest) {
    this.name = userRequest.name();
    this.email = userRequest.email();
    this.password_hash = userRequest.password();
    this.role = UserRole.USER;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if(this.role == UserRole.ADMIN)
      return List.of(
        new SimpleGrantedAuthority("ROLE_ADMIN"),
        new SimpleGrantedAuthority("ROLE_USER")
      );
    else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getPassword() {
    return null;
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
