package com.ecommerce.prices.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PricesRequest {
  @NotNull
  private LocalDateTime applicationDate;
  @NotNull
  private Long productId;
  @NotNull
  private Long brandId;
}
