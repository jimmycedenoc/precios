package com.ecommerce.prices.application.port.find;

import com.ecommerce.prices.domain.model.Price;
import com.ecommerce.prices.domain.model.PricesRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Validated
@RequestMapping("v1")
public interface PricesPort {
  @PostMapping(
      value = "/price",
      produces = "application/json",
      consumes = "application/json")
  ResponseEntity<Price> getPrice(@Valid @RequestBody PricesRequest pricesRequest);

}
