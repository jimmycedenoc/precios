package com.ecommerce.prices.infrastructure.config;

import com.ecommerce.prices.domain.port.output.PricesRepository;
import com.ecommerce.prices.domain.service.impl.PriceUserCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
  @Bean
  public PriceUserCase priceUserCase(final PricesRepository pricesRepository) {
    return new PriceUserCase(pricesRepository);
  }
}
