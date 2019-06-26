package com.example.storemanagement.repository;

import com.example.storemanagement.model.Iterm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItermRepository extends JpaRepository<Iterm, Long> {

  Optional<Iterm> findById(String id);

  @Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM Role c WHERE c.name = :name")
  Boolean itermExistsByName(@Param("name") final String name);

  Iterm findByName(String name);
}
