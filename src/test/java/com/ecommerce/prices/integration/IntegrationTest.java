package com.ecommerce.prices.integration;

import com.ecommerce.prices.application.port.find.PricesPort;
import com.ecommerce.prices.domain.model.ApplicationDate;
import com.ecommerce.prices.domain.model.Price;
import com.ecommerce.prices.domain.model.PricesRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class IntegrationTest {
  @Autowired
  private PricesPort pricesPort;

  @Test
  void testOne() {
    var startDate = LocalDateTime.parse("2020-06-14 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    var endDate = LocalDateTime.parse("2020-12-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    var expectedResult = Price.builder()
        .productIdentifier(35455L)
        .chainIdentifier(1L)
        .rateToApply(1L)
        .priceToApply(BigDecimal.valueOf(35.50).setScale(2, BigDecimal.ROUND_HALF_UP))
        .datesOfApplication(ApplicationDate.builder().startDate(startDate).endDate(endDate).build())
        .build();

    var pricesRequest = new PricesRequest();
    pricesRequest.setBrandId(1L);
    pricesRequest.setProductId(35455L);
    pricesRequest.setApplicationDate(LocalDateTime.parse("2020-06-14 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    var result = pricesPort.getPrice(pricesRequest);

    assertNotNull(result.getBody());
    assertEquals(expectedResult, result.getBody(), "Test 1: request at 10:00 a.m. on the 14th for product 35455 for brand 1 (XYZ)");
  }

  @Test
  void testTwo() {
    LocalDateTime startDate = LocalDateTime.parse("2020-06-14 15:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    LocalDateTime endDate = LocalDateTime.parse("2020-06-14 18:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    var expectedResult = Price.builder()
        .productIdentifier(35455L)
        .chainIdentifier(1L)
        .rateToApply(2L)
        .priceToApply(BigDecimal.valueOf(25.45).setScale(2, BigDecimal.ROUND_HALF_UP))
        .datesOfApplication(ApplicationDate.builder().startDate(startDate).endDate(endDate).build())
        .build();

    var pricesRequest = new PricesRequest();
    pricesRequest.setBrandId(1L);
    pricesRequest.setProductId(35455L);
    pricesRequest.setApplicationDate(LocalDateTime.parse("2020-06-14 16:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    var result = pricesPort.getPrice(pricesRequest);

    assertNotNull(result.getBody());
    assertEquals(expectedResult, result.getBody(), "Test 2: request at 4:00 p.m. on the 14th for product 35455 for brand 1 (XYZ)");
  }

  @Test
  void testThree() {
    var startDate = LocalDateTime.parse("2020-06-14 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    var endDate = LocalDateTime.parse("2020-12-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    var expectedResult = Price.builder()
        .productIdentifier(35455L)
        .chainIdentifier(1L)
        .rateToApply(1L)
        .priceToApply(BigDecimal.valueOf(35.50).setScale(2, BigDecimal.ROUND_HALF_UP))
        .datesOfApplication(ApplicationDate.builder().startDate(startDate).endDate(endDate).build())
        .build();

    var pricesRequest = new PricesRequest();
    pricesRequest.setBrandId(1L);
    pricesRequest.setProductId(35455L);
    pricesRequest.setApplicationDate(LocalDateTime.parse("2020-06-14 21:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    var result = pricesPort.getPrice(pricesRequest);

    assertNotNull(result.getBody());
    assertEquals(expectedResult, result.getBody(), "Test 3: request at 9:00 p.m. on day 14th for product 35455 for brand 1 (XYZ)");
  }

  @Test
  void testFour() {
    var startDate = LocalDateTime.parse("2020-06-15 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    var endDate = LocalDateTime.parse("2020-06-15 11:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    var expectedResult = Price.builder()
        .productIdentifier(35455L)
        .chainIdentifier(1L)
        .rateToApply(3L)
        .priceToApply(BigDecimal.valueOf(30.50).setScale(2, BigDecimal.ROUND_HALF_UP))
        .datesOfApplication(ApplicationDate.builder().startDate(startDate).endDate(endDate).build())
        .build();

    var pricesRequest = new PricesRequest();
    pricesRequest.setBrandId(1L);
    pricesRequest.setProductId(35455L);
    pricesRequest.setApplicationDate(LocalDateTime.parse("2020-06-15 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    var result = pricesPort.getPrice(pricesRequest);

    assertNotNull(result.getBody());
    assertEquals(expectedResult, result.getBody(), "Test 4: request at 10:00 a.m. on the 15th for product 35455 for brand 1 (XYZ)");
  }

  @Test
  void testFive() {
    var startDate = LocalDateTime.parse("2020-06-15 16:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    var endDate = LocalDateTime.parse("2020-12-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    var expectedResult = Price.builder()
        .productIdentifier(35455L)
        .chainIdentifier(1L)
        .rateToApply(4L)
        .priceToApply(BigDecimal.valueOf(38.95).setScale(2, BigDecimal.ROUND_HALF_UP))
        .datesOfApplication(ApplicationDate.builder().startDate(startDate).endDate(endDate).build())
        .build();

    var pricesRequest = new PricesRequest();
    pricesRequest.setBrandId(1L);
    pricesRequest.setProductId(35455L);
    pricesRequest.setApplicationDate(LocalDateTime.parse("2020-06-16 21:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    var result = pricesPort.getPrice(pricesRequest);

    assertNotNull(result.getBody());
    assertEquals(expectedResult, result.getBody(), "Test 5: request at 9:00 p.m. on day 16th for product 35455 for brand 1 (XYZ)");
  }
}
