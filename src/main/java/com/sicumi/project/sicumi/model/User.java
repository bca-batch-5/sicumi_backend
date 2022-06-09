package com.sicumi.project.sicumi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true)
    private String email;

    @Column(unique = true, nullable = true, length = 20)
    private String username;

    @Column(nullable = true)
    private String firstname;

    @Column
    private String lastname;

    @Column(nullable = true)
    private String password;

    @Column(nullable = true, length = 6)
    private int pin;

    @Column(nullable = true, length = 13)
    private int phone;

    public User(String email, String username, String firstname, String lastname, String password, int pin, int phone) {
        this.email = email;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.pin = pin;
        this.phone = phone;
    }

    private Boolean isDeleted = false;

}
