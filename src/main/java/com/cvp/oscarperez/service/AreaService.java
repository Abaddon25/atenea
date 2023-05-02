package com.cvp.oscarperez.service;

import com.cvp.oscarperez.dto.AreaDto;
import com.cvp.oscarperez.entities.Area;
import com.cvp.oscarperez.entities.Asset;
import com.cvp.oscarperez.repository.AreaRepository;
import com.cvp.oscarperez.repository.AssetRepository;
import com.cvp.oscarperez.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {

  @Autowired
  AreaRepository areaRepository;
  @Autowired
  CityRepository cityRepository;
  @Autowired
  AssetRepository assetRepository;

  public List<Area> getAll() {
    return areaRepository.findAll();
  }


  public Object addOne(AreaDto area) {
    if (areaRepository.existsByName(area.getName())) {
      return "Exists";
    }
    if (!cityRepository.existsById(area.getCity().getId())){
      return "City not found";
    }
    if (area.getAssets() != null && area.getAssets().size() > 0) {
      if (!assetRepository.existsByIdIn(area.getAssets().stream().map(Asset::getId).toList())) {
        return "Assets not found";
      }
    }

    Area areaNew = Area.builder()
        .name(area.getName())
        .description(area.getDescription())
        .city(area.getCity())
        .assets(area.getAssets())
        .build();
    return areaRepository.save(areaNew);
  }
  public Object updateOne(String id, AreaDto area) {
    if (!areaRepository.existsById(id)) {
      return "Not found";
    }
    if (!cityRepository.existsById(area.getCity().getId())){
      return "City not found";
    }
    if (area.getAssets() != null && area.getAssets().size() > 0) {
      if (!assetRepository.existsByIdIn(area.getAssets().stream().map(Asset::getId).toList())) {
        return "Assets not found";
      }
    }
    Area areaNew = Area.builder()
        .id(id)
        .name(area.getName())
        .description(area.getDescription())
        .city(area.getCity())
        .assets(area.getAssets())
        .build();
    return areaRepository.save(areaNew);
  }
  public Object assignAssets (String id, AreaDto area) {
    if (!areaRepository.existsById(id)) {
      return "Not found";
    }
    if (!cityRepository.existsById(area.getCity().getId())){
      return "City not found";
    }
    if (!assetRepository.existsByIdIn(area.getAssets().stream().map(Asset::getId).toList())){
      return "Assets not found";
    }
    Area areaOld = areaRepository.findById(id).get();
    Area areaNew = Area.builder()
        .id(id)
        .name(areaOld.getName())
        .description(areaOld.getDescription())
        .city(areaOld.getCity())
        .assets(area.getAssets())
        .build();
    return areaRepository.save(areaNew);
  }
}
