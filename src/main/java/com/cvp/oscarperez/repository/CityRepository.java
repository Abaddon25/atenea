package com.cvp.oscarperez.repository;

import com.cvp.oscarperez.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, String> {
}