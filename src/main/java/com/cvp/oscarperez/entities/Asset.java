package com.cvp.oscarperez.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "activo")
public class Asset {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
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
  @Temporal(TemporalType.DATE)
  private LocalDate purchaseDate;
  @Temporal(TemporalType.DATE)
  private LocalDate dischargeDate;
  private String state;
  private String color;
}