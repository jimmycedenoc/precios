package com.ecommerce.prices.infrastructure.etity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "BRAND")
public class Brand {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
}
