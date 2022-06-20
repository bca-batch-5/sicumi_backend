package com.sicumi.project.sicumi.model;

import java.util.Date;

import javax.persistence.Column;
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

    @Column (name = "date_time")
    private Date dateTime;

    @Column(name = "trans_type")
    private String transType;

    @Column (name ="status")
    private String status;

    @Column (name = "transAmount")
    private Integer Amount;

    @Column (name = "notes")
    private String notes;

    public Transaction(User senderId, User receiverId, Date dateTime, String transType, Integer Amount, String notes){
        this.senderId=senderId;
        this.receiverId=receiverId;
        this.dateTime=dateTime;
        this.transType=transType;
        this.Amount=Amount;
        this.notes= notes;
    }
}
