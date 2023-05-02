package com.cvp.oscarperez.dto;

import com.cvp.oscarperez.entities.Asset;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PersonDto {
  private String identification;
  private String name;
  private String lastName;
  private List<Asset> assets;
}
