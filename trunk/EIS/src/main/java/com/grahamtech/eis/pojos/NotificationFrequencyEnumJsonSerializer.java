package com.grahamtech.eis.pojos;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class NotificationFrequencyEnumJsonSerializer extends
    JsonSerializer<NotificationFrequencyEnum> {

  @Override
  public void serialize(NotificationFrequencyEnum value,
      JsonGenerator generator,
      SerializerProvider provider) throws IOException, JsonProcessingException {

    generator.writeStartObject();
    generator.writeFieldName("Enum Name");
    generator.writeNumber(value.getEnumString());
    generator.writeEndObject();
  }
}
