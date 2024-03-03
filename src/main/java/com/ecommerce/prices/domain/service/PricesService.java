package com.ecommerce.prices.domain.service;

import com.ecommerce.prices.domain.model.Price;
import com.ecommerce.prices.domain.model.PricesRequest;

public interface PricesService {
  Price getPrice(PricesRequest pricesRequest);
}
