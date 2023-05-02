package com.cvp.oscarperez.controller;

import com.cvp.oscarperez.common.ResponseHandler;
import com.cvp.oscarperez.dto.AreaDto;
import com.cvp.oscarperez.entities.Area;
import com.cvp.oscarperez.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areas")
public class AreaController {
  @Autowired
  AreaService areaService;

  @GetMapping
  public ResponseEntity<?> getAll() {
    List<Area> data = areaService.getAll();
    return ResponseHandler.generateResponse("Areas cargadas correctamente", HttpStatus.OK, data);
  }

  @PostMapping
  public ResponseEntity<?> addOne(@RequestBody AreaDto area) {
    Object data = areaService.addOne(area);
    if (data == "Exists") {
      return ResponseHandler.generateResponse("Nombre del area ya existe", HttpStatus.CONFLICT, null);
    } else if (data == "City not found") {
      return ResponseHandler.generateResponse("Ciudad no existe", HttpStatus.NOT_FOUND, null);
    } else if (data == "Assets not found") {
      return ResponseHandler.generateResponse("Activos no existen", HttpStatus.NOT_FOUND, null);
    }
    return ResponseHandler.generateResponse("Area guardada correctamente", HttpStatus.OK, data);
  }
  @PutMapping("/{id}")
  public ResponseEntity<?> updateOne(@PathVariable String id, @RequestBody AreaDto area) {
    Object data = areaService.updateOne(id, area);
    if (data == "Not found") {
      return ResponseHandler.generateResponse("Area no existe", HttpStatus.NOT_FOUND, null);
    } else if (data == "City not found") {
      return ResponseHandler.generateResponse("Ciudad no existe", HttpStatus.NOT_FOUND, null);
    } else if (data == "Assets not found") {
      return ResponseHandler.generateResponse("Activos no existen", HttpStatus.NOT_FOUND, null);
    }
    return ResponseHandler.generateResponse("Area actualizada correctamente", HttpStatus.OK, data);
  }

  @PutMapping("/assignAssets/{id}")
  public ResponseEntity<?> assignAssets(@PathVariable String id, @RequestBody AreaDto area) {
    Object data = areaService.assignAssets(id, area);
    if (data == "Not found") {
      return ResponseHandler.generateResponse("Area no existe", HttpStatus.NOT_FOUND, null);
    } else if (data == "City not found") {
      return ResponseHandler.generateResponse("Ciudad no existe", HttpStatus.NOT_FOUND, null);
    } else if (data == "Assets not found") {
      return ResponseHandler.generateResponse("Activos no existen", HttpStatus.NOT_FOUND, null);
    }
    return ResponseHandler.generateResponse("Area actualizada correctamente", HttpStatus.OK, data);
  }
}
