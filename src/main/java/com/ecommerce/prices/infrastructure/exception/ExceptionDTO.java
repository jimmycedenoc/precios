package com.ecommerce.prices.infrastructure.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDTO {
  private String errorCode;
  private String message;
  private Object details;
}
