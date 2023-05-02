package com.cvp.oscarperez.controller;

import com.cvp.oscarperez.common.ResponseHandler;
import com.cvp.oscarperez.dto.PersonDto;
import com.cvp.oscarperez.entities.Person;
import com.cvp.oscarperez.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

  @Autowired
  PersonService personService;

  @GetMapping
  public ResponseEntity<?> getAll() {
    List<Person> data = personService.getAll();
    return ResponseHandler.generateResponse("Personas cargados correctamente", HttpStatus.OK, data);
  }

  @PostMapping
  public ResponseEntity<?> addOne(@RequestBody PersonDto person) {
    Object data = personService.addOne(person);
    if (data == "Identification not provided") {
      return ResponseHandler.generateResponse("Identificacion no suministrada", HttpStatus.NO_CONTENT, null);
    } else if (data == "it already exists") {
      return ResponseHandler.generateResponse("Identificacion ya existe", HttpStatus.CONFLICT, null);
    } else if (data == "Assets not found") {
      return ResponseHandler.generateResponse("Activos no encontrados", HttpStatus.NOT_FOUND, null);
    }
    return ResponseHandler.generateResponse("Persona guardada correctamente", HttpStatus.OK, data);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateOne(@PathVariable String id, @RequestBody PersonDto person) {
    Object data = personService.updateOne(id, person);
    if (data == "Not found") {
      return ResponseHandler.generateResponse("Persona no encontreda", HttpStatus.NOT_FOUND, null);
    } else if (data == "Assets not found") {
      return ResponseHandler.generateResponse("Activos no encontrados", HttpStatus.NOT_FOUND, null);
    }
    return ResponseHandler.generateResponse("Persona actualizada correctamente", HttpStatus.OK, data);
  }
  @PutMapping("/assignAssets/{id}")
  public ResponseEntity<?> assignAssets(@PathVariable String id, @RequestBody PersonDto person) {
    Object data = personService.assignAssets(id, person);
    if (data == "Not found") {
      return ResponseHandler.generateResponse("Persona no encontreda", HttpStatus.NOT_FOUND, null);
    } else if (data == "Assets not found") {
      return ResponseHandler.generateResponse("Activos no encontrados", HttpStatus.NOT_FOUND, null);
    }
    return ResponseHandler.generateResponse("Activos asignados correctamente", HttpStatus.OK, data);
  }
}
