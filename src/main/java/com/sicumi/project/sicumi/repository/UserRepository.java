package com.sicumi.project.sicumi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sicumi.project.sicumi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  @Query(value = "select * from users where email = ?1", nativeQuery = true)
  List<User> findByEmail(String email);
}
