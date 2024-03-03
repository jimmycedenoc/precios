package com.ecommerce.prices.domain.service.impl;

import com.ecommerce.prices.domain.model.ApplicationDate;
import com.ecommerce.prices.domain.model.Price;
import com.ecommerce.prices.domain.model.PricesRequest;
import com.ecommerce.prices.domain.port.output.PricesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = PriceUserCase.class)
@ExtendWith(SpringExtension.class)
class PriceUserCaseTest {
  @Autowired
  private PriceUserCase priceUserCase;

  @MockBean
  private PricesRepository pricesRepository;

  @Test
  void getPrice_ValidRequest_ReturnsPrice() {
      var startDate = LocalDateTime.parse("2020-06-14 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      var endDate = LocalDateTime.parse("2020-12-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    var request = new PricesRequest();
    var expectedPrice = Price.builder()
        .productIdentifier(35455L)
        .chainIdentifier(1L)
        .rateToApply(1L)
        .priceToApply(BigDecimal.valueOf(35.50))
        .datesOfApplication(ApplicationDate.builder().startDate(startDate).endDate(endDate).build())
        .build();
    when(pricesRepository.findPrices(any(), any(), any())).thenReturn(expectedPrice);

    var result = priceUserCase.getPrice(request);

    assertEquals(expectedPrice, result);
    assertEquals(expectedPrice.getPriceToApply(), result.getPriceToApply());
    assertEquals(expectedPrice.getChainIdentifier(), result.getChainIdentifier());
    assertEquals(expectedPrice.getRateToApply(), result.getRateToApply());
    assertEquals(expectedPrice.getProductIdentifier(), result.getProductIdentifier());
    assertEquals(expectedPrice.getDatesOfApplication(), result.getDatesOfApplication());
    assertEquals(expectedPrice.getDatesOfApplication().getStartDate(), result.getDatesOfApplication().getStartDate());
    assertEquals(expectedPrice.getDatesOfApplication().getEndDate(), result.getDatesOfApplication().getEndDate());
  }

  @Test
  void getPrice_InvalidRequest_ReturnsNull() {
    var request = new PricesRequest();
    when(pricesRepository.findPrices(request.getApplicationDate(), request.getProductId(), request.getBrandId())).thenReturn(null);

    Price result = priceUserCase.getPrice(request);

    assertEquals(null, result);
  }
}