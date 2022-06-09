package com.sicumi.project.sicumi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(unique = true)
  private String email;

  private String name;

  private String password;

  @Column(nullable = true)
  private Integer pin;

  private Boolean isActive = true;

  public User( String name, String email, String password){
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public User(){}
}
