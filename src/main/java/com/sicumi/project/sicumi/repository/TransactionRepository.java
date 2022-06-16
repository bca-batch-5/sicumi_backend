package com.sicumi.project.sicumi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sicumi.project.sicumi.model.DetailUser;
import com.sicumi.project.sicumi.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer>{
    List<Transaction> getBySenderIdId(Integer userId);

    List<Transaction> getByReceiverIdId(Integer userId);
   
    @Query(value = "select dayname(a.date_time)as hari, sum(trans_amount) as amount from transaction a WHERE sender_id = ? and date_time BETWEEN now() - INTERVAL 7 DAY AND now() GROUP by hari" , nativeQuery = true)
    List<Object> getTransactionDaily (Integer senderId);

   @Query(value = "select * from detail_user b WHERE b.id_user In (select receiver_id from transaction where sender_id = ? )",nativeQuery = true)
    List<Object> getContact (Integer sendeId);
}
