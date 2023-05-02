package com.cvp.oscarperez.service;

import com.cvp.oscarperez.dto.AssetDto;
import com.cvp.oscarperez.entities.Asset;
import com.cvp.oscarperez.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AssetService {

  @Autowired
  AssetRepository assetRepository;

  public List<Asset> getAll() {
    return assetRepository.findAll();
  }

  public List<Asset> getAssets(String _type, String answer) throws ParseException {
    List<Asset> data = null;
    switch (_type) {
      case "type": {
        data = assetRepository.getByType(answer);
        break;
      }
      case "purchaseDate": {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(answer, formatter);
        data = assetRepository.getByPurchaseDate(localDate);
        break;
      }
      case "serial" :{
        data = assetRepository.getBySerial(answer);
        break;
      }
    }

    if (data.isEmpty()) {
      return null;
    }
    return data;
  }

  public Asset addOne(AssetDto asset) {
    if (assetRepository.existsByName(asset.getName())) {
      return null;
    }
    Asset assetNew = Asset.builder()
        .name(asset.getName())
        .description(asset.getDescription())
        .type(asset.getType())
        .serial(asset.getSerial())
        .internalInventoryNumber(asset.getInternalInventoryNumber())
        .size(asset.getSize())
        .high(asset.getHigh())
        .broad(asset.getBroad())
        .longAsset(asset.getLongAsset())
        .purchaseValue(asset.getPurchaseValue())
        .purchaseDate(asset.getPurchaseDate())
        .dischargeDate(asset.getDischargeDate())
        .state(asset.getState())
        .color(asset.getColor())
        .build();
    return assetRepository.save(assetNew);
  }

  public Object updateOne(String id, AssetDto asset) {
    if (!assetRepository.existsById(id)) {
      return "Not found";
    }
    Asset assetOld = assetRepository.findById(id).get();

    if (assetOld.getPurchaseDate().isAfter(asset.getDischargeDate())) {
      return "date is less than the purchase date";
    }

    Asset assetUpdate = Asset.builder()
        .id(id)
        .name(assetOld.getName())
        .description(assetOld.getDescription())
        .type(assetOld.getType())
        .serial(assetOld.getSerial())
        .internalInventoryNumber(asset.getInternalInventoryNumber())
        .size(assetOld.getSize())
        .high(assetOld.getHigh())
        .broad(assetOld.getBroad())
        .longAsset(assetOld.getLongAsset())
        .purchaseValue(assetOld.getPurchaseValue())
        .purchaseDate(assetOld.getPurchaseDate())
        .dischargeDate(asset.getDischargeDate())
        .state(assetOld.getState())
        .color(assetOld.getColor())
        .build();
    return assetRepository.save(assetUpdate);
  }
}
