package com.sicumi.project.sicumi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sicumi.project.sicumi.model.TopUp;

@Repository
public interface TopUpRepository extends JpaRepository<TopUp, Integer>{
    
}
