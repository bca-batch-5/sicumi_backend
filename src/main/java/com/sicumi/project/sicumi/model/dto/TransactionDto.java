package com.sicumi.project.sicumi.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Integer senderId;
    private Integer receiverId;
    private Date dateTime;
    private String transType;
    private String status;
    private Integer transAmount;
    private String notes;
}
