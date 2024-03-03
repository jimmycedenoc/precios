package com.ecommerce.prices.infrastructure.adapter.input;

import com.ecommerce.prices.application.port.find.PricesPort;
import com.ecommerce.prices.domain.model.Price;
import com.ecommerce.prices.domain.model.PricesRequest;
import com.ecommerce.prices.domain.service.PricesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@AllArgsConstructor
public class PricesAdapter implements PricesPort {

  private PricesService pricesService;

  @Override
  public ResponseEntity<Price> getPrice(PricesRequest pricesRequest) {
    return Optional.of(pricesRequest)
        .map(pricesService::getPrice)
        .map(ResponseEntity::ok)
        .orElseThrow();
  }
}
