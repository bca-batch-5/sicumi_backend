package com.sicumi.project.sicumi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sicumi.project.sicumi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  @Query(value = "select * from users where email = ?1", nativeQuery = true)
  List<User> findByEmail(String email);

Optional<User> findById(Integer senderId);
}
