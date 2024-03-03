package com.ecommerce.prices.domain.port.output;

import com.ecommerce.prices.domain.model.Price;

import java.time.LocalDateTime;

public interface PricesRepository {
  Price findPrices(LocalDateTime date, Long productId, Long brandId);
}
