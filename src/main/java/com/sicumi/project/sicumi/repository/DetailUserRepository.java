package com.sicumi.project.sicumi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sicumi.project.sicumi.model.DetailUser;

@Repository
public interface DetailUserRepository extends JpaRepository<DetailUser, Integer>{

    DetailUser getByUserIdId(Integer userId);

    Optional<DetailUser> findByIdPhone(String phone);
    
}
