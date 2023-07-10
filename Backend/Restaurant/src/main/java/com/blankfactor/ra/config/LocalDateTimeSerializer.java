package com.blankfactor.ra.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Value("${ra.app.time-zone}")
    private String timeZone;

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        ZoneId zoneId = ZoneId.of(timeZone);
        LocalDateTime localDateTime = value.atZone(zoneId).toLocalDateTime();
        String formattedDateTime = FORMATTER.format(localDateTime);
        gen.writeString(formattedDateTime);
    }
}