package com.sicumi.project.sicumi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String email;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private Integer pin;
    private Integer phone;

}
