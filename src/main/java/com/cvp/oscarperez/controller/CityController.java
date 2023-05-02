package com.cvp.oscarperez.controller;

import com.cvp.oscarperez.common.ResponseHandler;
import com.cvp.oscarperez.entities.City;
import com.cvp.oscarperez.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

  @Autowired
  CityService cityService;

  @GetMapping
  public ResponseEntity<?> getAll() {
    List<City> data = cityService.getAll();
    return ResponseHandler.generateResponse("Ciudades cargadas correctamente", HttpStatus.OK, data);
  }
}
