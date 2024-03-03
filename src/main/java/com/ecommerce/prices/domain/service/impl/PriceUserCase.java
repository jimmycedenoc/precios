package com.ecommerce.prices.domain.service.impl;

import com.ecommerce.prices.domain.model.Price;
import com.ecommerce.prices.domain.model.PricesRequest;
import com.ecommerce.prices.domain.port.output.PricesRepository;
import com.ecommerce.prices.domain.service.PricesService;

public class PriceUserCase implements PricesService {
  private PricesRepository priceRepository;

  public PriceUserCase(PricesRepository priceRepository) {
    this.priceRepository = priceRepository;
  }

  @Override
  public Price getPrice(PricesRequest pricesRequest) {
    return priceRepository.findPrices(pricesRequest.getApplicationDate(), pricesRequest.getProductId(), pricesRequest.getBrandId());
  }
}
