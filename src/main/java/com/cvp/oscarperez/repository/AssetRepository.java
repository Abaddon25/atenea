package com.cvp.oscarperez.repository;

import com.cvp.oscarperez.entities.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, String> {

  public boolean existsByName(String name);
  public List<Asset> getByType(String type);
  public List<Asset> getByPurchaseDate(LocalDate purchaseDate);
  public List<Asset> getBySerial(String serial);

  public boolean existsByIdIn(List<String> ids);
}