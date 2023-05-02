package com.cvp.oscarperez.service;

import com.cvp.oscarperez.entities.City;
import com.cvp.oscarperez.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
  @Autowired
  CityRepository cityRepository;

  public List<City> getAll() {
    return cityRepository.findAll();
  }
}
