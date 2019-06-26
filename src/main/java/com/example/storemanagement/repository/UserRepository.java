package com.example.storemanagement.repository;

import com.example.storemanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


  Optional<User> findByUserName(String userName);

  @Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM User c WHERE c.userName = :userName")
  Boolean existsByUserName(@Param("userName") final String userName);
}
