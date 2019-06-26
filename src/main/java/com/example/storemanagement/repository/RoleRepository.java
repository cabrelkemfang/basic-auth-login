package com.example.storemanagement.repository;

import com.example.storemanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

  @Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM Role c WHERE c.name = :name")
  Boolean existsByName(@Param("name") final String name);

  Optional<Role> findByName(String name);
}
