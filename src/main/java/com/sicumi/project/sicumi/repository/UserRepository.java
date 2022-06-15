package com.sicumi.project.sicumi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sicumi.project.sicumi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    
}
