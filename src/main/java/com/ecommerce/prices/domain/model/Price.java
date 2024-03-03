package com.ecommerce.prices.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class Price {
  private Long productIdentifier;
  private Long chainIdentifier;
  private Long rateToApply;
  private BigDecimal priceToApply;
  private ApplicationDate datesOfApplication;

}
