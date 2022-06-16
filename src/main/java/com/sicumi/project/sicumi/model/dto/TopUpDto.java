package com.sicumi.project.sicumi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopUpDto {
    private String source;
    private Integer amount;
    private String phone;
}
