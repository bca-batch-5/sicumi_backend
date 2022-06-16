package com.sicumi.project.sicumi.model;

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
@Table(name = "topUp")
@NoArgsConstructor
@Data
public class TopUp {
   @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
   public  Integer Id;
   
   public String source;

   public Integer amount;

   @ManyToOne
   @JoinColumn (name = "phone")
   public DetailUser phone;

   public TopUp(String source, Integer amount, DetailUser phone ){
       this.source=source;
       this.amount = amount;
       this.phone = phone;
   }
}
