package com.sicumi.project.sicumi.model;

import java.util.Date;

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

  @Column(unique = true, name = "email")
  private String email;

  @Column (name = "name")
  private String name;

  @Column (name = "password")
  private String password;

  @Column(nullable = true, name = "pin")
  private String pin;

  private Boolean is_active = true;

  private Date created_on = new Date();

  private Date last_update = new Date();

  public User( String name, String email, String password, String pin){
    this.name = name;
    this.email = email;
    this.password = password;
    this.pin = pin;
  }

  public User(){}
}
