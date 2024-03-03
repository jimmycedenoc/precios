package com.ecommerce.prices.infrastructure.persistence;

import com.ecommerce.prices.domain.model.Price;
import com.ecommerce.prices.domain.port.output.PricesRepository;
import com.ecommerce.prices.infrastructure.etity.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface InMemoryCachePricesRepository extends JpaRepository<Prices, Long>, PricesRepository {
  @Override
  @Query("SELECT new com.ecommerce.prices.domain.model.Price(p.productId, p.brandId, p.priceList, p.price, new com.ecommerce.prices.domain.model.ApplicationDate(p.startDate, p.endDate)) FROM Prices p where :date between p.startDate and p.endDate and p.productId = :productId and p.brandId = :brandId order by priority desc limit 1")
  Price findPrices(LocalDateTime date, Long productId, Long brandId);
}
