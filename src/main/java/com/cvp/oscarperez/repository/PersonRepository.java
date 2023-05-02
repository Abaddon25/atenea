package com.cvp.oscarperez.repository;

import com.cvp.oscarperez.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

  public boolean existsByIdentification(String identification);
}