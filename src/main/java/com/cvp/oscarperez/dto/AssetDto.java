package com.cvp.oscarperez.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AssetDto {
  private String name;
  private String description;
  private String type;
  private String serial;
  private Integer internalInventoryNumber;
  private Integer size;
  private Integer high;
  private Integer broad;
  private Integer longAsset;
  private Double purchaseValue;
  private LocalDate purchaseDate;
  private LocalDate dischargeDate;
  private String state;
  private String color;
}
