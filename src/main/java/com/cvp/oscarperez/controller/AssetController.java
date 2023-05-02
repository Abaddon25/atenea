package com.cvp.oscarperez.controller;

import com.cvp.oscarperez.common.ResponseHandler;
import com.cvp.oscarperez.dto.AssetDto;
import com.cvp.oscarperez.entities.Asset;
import com.cvp.oscarperez.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

  @Autowired
  AssetService assetService;

  @GetMapping
  public ResponseEntity<?> getAll() {
    List<Asset> data = assetService.getAll();
    return ResponseHandler.generateResponse("Activos cargados correctamente", HttpStatus.OK, data);
  }

  @GetMapping("/{type}/{answer}")
  public ResponseEntity<?> retrieve(@PathVariable String type, @PathVariable String answer) throws ParseException {
    List<Asset> data = assetService.getAssets(type, answer);
    if (data == null) {
      return ResponseHandler.generateResponse("Activo no encontrado", HttpStatus.NOT_FOUND, null);
    }

    return ResponseHandler.generateResponse("Activos encontrados", HttpStatus.OK, data);
  }

  @PostMapping
  public ResponseEntity<?> addOne(@RequestBody AssetDto assetDto) {
    Asset data = assetService.addOne(assetDto);
    if (data == null) {
      return ResponseHandler.generateResponse("Nombre del activo ya existe", HttpStatus.CONFLICT, null);
    }
    return ResponseHandler.generateResponse("Activo guardado correctamente", HttpStatus.OK, data);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateOne(@PathVariable String id, @RequestBody AssetDto asset) {
    Object data = assetService.updateOne(id, asset);
    if (data == "Not found") {
      return ResponseHandler.generateResponse("Activo no encontrado", HttpStatus.NOT_FOUND, null);
    } else if (data == "date is less than the purchase date") {
      return ResponseHandler.generateResponse("fecha de baja es inferior a la fecha de compra", HttpStatus.CONFLICT, null);
    }
    return ResponseHandler.generateResponse("Activo actualizado correctamente", HttpStatus.OK, data);
  }
}
