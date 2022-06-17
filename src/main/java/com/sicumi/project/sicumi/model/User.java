package com.sicumi.project.sicumi.model;

import java.sql.Date;

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

    @Column(nullable = true)
    private String password;

    @Column(nullable = true, length = 6)
    private Integer pin;

    private Boolean isActive = true;
    private Date createdOn;
    private Date lastUpdate;

    public User(String email, String password, Integer pin, Boolean isActive, Date createdOn,
            Date lastUpdate) {
        this.email = email;
        this.password = password;
        this.pin = pin;
        this.isActive = isActive;
        this.createdOn = createdOn;
        this.lastUpdate = lastUpdate;
    }

}
