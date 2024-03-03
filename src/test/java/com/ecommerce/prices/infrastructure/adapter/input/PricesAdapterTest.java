package com.ecommerce.prices.infrastructure.adapter.input;

import com.ecommerce.prices.domain.model.ApplicationDate;
import com.ecommerce.prices.domain.model.Price;
import com.ecommerce.prices.domain.model.PricesRequest;
import com.ecommerce.prices.domain.service.PricesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = PricesAdapter.class)
@ExtendWith(SpringExtension.class)
class PricesAdapterTest {
  @Autowired
  private PricesAdapter pricesAdapter;

  @MockBean
  private PricesService pricesService;

  @Test
  void getPrice_ValidRequest_ReturnsOkResponse() {
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

    when(pricesService.getPrice(any())).thenReturn(expectedPrice);

    var response = pricesAdapter.getPrice(new PricesRequest());

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(expectedPrice, response.getBody());
  }

  @Test
  void getPrice_InvalidRequest_ThrowsException() {
    var request = new PricesRequest();
    when(pricesService.getPrice(request)).thenThrow(NoSuchElementException.class);
    assertThrows(NoSuchElementException.class, () -> pricesAdapter.getPrice(request));
  }
}