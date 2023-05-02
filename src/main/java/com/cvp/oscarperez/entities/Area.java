package com.cvp.oscarperez.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Area")
public class Area {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  private String name;
  private String description;

  @OneToOne
  @JoinColumn(name = "city_id")
  private City city;

  @OneToMany
  @JoinColumn(name = "id_asset")
  private List<Asset> assets;
}