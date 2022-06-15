package com.sicumi.project.sicumi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "detail_user")
public class DetailUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true)
    private String firstname;

    @Column
    private String lastname;

    @Column(nullable = true, length = 13, unique = true)
    private String phone;

    private String photo;
    private Integer balance;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User userId;

    public DetailUser(String firstname, String lastname, String phone, String photo, Integer balance, User userId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.photo = photo;
        this.balance = balance;
        this.userId = userId;
    }

}