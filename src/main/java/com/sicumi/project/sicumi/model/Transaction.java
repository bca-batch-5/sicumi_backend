package com.sicumi.project.sicumi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User senderId;

    @ManyToOne
    @JoinColumn (name =  "receiver_id")
    private User receiverId;

    private Date dateTime;

    private String transType;

    private String status;

    private Integer transAmount;

    private String notes;

    public Transaction(User senderId, User receiverId, Date dateTime, String transType, Integer transAmount, String notes){
        this.senderId=senderId;
        this.receiverId=receiverId;
        this.dateTime=dateTime;
        this.transType=transType;
        this.transAmount=transAmount;
        this.notes= notes;
    }
}
