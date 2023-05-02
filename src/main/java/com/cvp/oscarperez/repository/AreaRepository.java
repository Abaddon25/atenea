package com.cvp.oscarperez.repository;

import com.cvp.oscarperez.entities.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<Area, String> {

  public boolean existsByName(String name);
}