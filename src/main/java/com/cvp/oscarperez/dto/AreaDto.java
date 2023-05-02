package com.cvp.oscarperez.dto;

import com.cvp.oscarperez.entities.Asset;
import com.cvp.oscarperez.entities.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AreaDto {
  private String name;
  private String description;
  private City city;
  private List<Asset> assets;
}
