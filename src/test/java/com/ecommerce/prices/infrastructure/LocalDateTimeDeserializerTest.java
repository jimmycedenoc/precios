package com.ecommerce.prices.infrastructure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ContextConfiguration(classes = LocalDateTimeDeserializer.class)
class LocalDateTimeDeserializerTest {
  private LocalDateTimeDeserializer deserializer;
  private JsonParser jsonParser;
  private DeserializationContext deserializationContext;

  @BeforeEach
  void setUp() {
    deserializer = new LocalDateTimeDeserializer();
    jsonParser = mock(JsonParser.class);
    deserializationContext = mock(DeserializationContext.class);
  }

  @Test
  void deserialize_ValidDateTime_ReturnsLocalDateTime() throws IOException {
    var dateString = "2020-06-14 00:00:00";
    when(jsonParser.getText()).thenReturn(dateString);

    var result = deserializer.deserialize(jsonParser, deserializationContext);

    var expected = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    assertEquals(expected, result);
  }

  @Test
  void deserialize_InvalidDateTime_ThrowsIOException() throws IOException {
    var invalidDateString = "2020-06-14";
    when(jsonParser.getText()).thenReturn(invalidDateString);

    assertThrows(DateTimeParseException.class, () -> deserializer.deserialize(jsonParser, deserializationContext));
  }
}