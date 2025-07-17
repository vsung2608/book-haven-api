package com.bookhaven.interaction_service.util;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class TimeFormatter {

    private final Map<Long, Function<Instant, String>> strategyMap = new LinkedHashMap<>();

    public TimeFormatter(){
        strategyMap.put(60L, this::formatInSeconds);
        strategyMap.put(3600L, this::formatInMinutes);
        strategyMap.put(86400L, this::formatInHours);
        strategyMap.put(Long.MAX_VALUE, this::formatInDays);
    }

    public String formatInSeconds(Instant instant){
        var seconds = ChronoUnit.SECONDS.between(instant, Instant.now());
        return "%d giây trước".formatted(seconds);
    }

    public String formatInMinutes(Instant instant){
        var minutes = ChronoUnit.MINUTES.between(instant, Instant.now());
        return "%d phút trước".formatted(minutes);
    }

    public String formatInHours(Instant instant){
        var hours = ChronoUnit.HOURS.between(instant, Instant.now());
        return "%d giờ trước".formatted(hours);
    }

    public String formatInDays(Instant instant){
        LocalDateTime ldt = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                .withZone(ZoneId.systemDefault());;
        return ldt.format(formatter);
    }

    public String format(Instant instant) {
        long seconds = ChronoUnit.SECONDS.between(instant, Instant.now());
        return strategyMap.entrySet().stream()
                .filter(longFunctionEntry -> seconds < longFunctionEntry.getKey())
                .findFirst().get().getValue().apply(instant);
    }
}

