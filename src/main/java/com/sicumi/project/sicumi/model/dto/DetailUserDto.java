package com.sicumi.project.sicumi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailUserDto {
    private String firstname;
    private String lastname;
    private String phone;
    private String photo;
    private Integer balance;
    private Integer userId;
}
