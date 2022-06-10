package com.sicumi.project.sicumi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    // same as table
    private Integer id;

    private String email;
    private String username;
    private String firstname;
    private String lastname;
    private String phone;

    // password
    private String currentpassword;
    private String newpassword;
    private String renewpassword;

    // pin
    private String pin1;
    private String pin2;
    private String pin3;
    private String pin4;
    private String pin5;
    private String pin6;

    // img
    private String Photo;

}
