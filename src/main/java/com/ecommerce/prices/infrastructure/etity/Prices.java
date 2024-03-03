package com.ecommerce.prices.infrastructure.etity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PRICES")
public class Prices {
  @Id
  @GeneratedValue
  @Column(name = "PRICE_LIST")
  private Long priceList;
  @Column(name = "BRAND_ID")
  private Long brandId;
  @Column(name = "START_DATE")
  private LocalDateTime startDate;
  @Column(name = "END_DATE")
  private LocalDateTime endDate;
  @Column(name = "PRODUCT_ID")
  private Long productId;
  @Column(name = "PRIORITY")
  private Long priority;
  @Column(name = "PRICE")
  private BigDecimal price;
  @Column(name = "CURR")
  private String currency;
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "BRAND_ID", referencedColumnName = "id", insertable = false, updatable = false)
  private Brand brand;

}
