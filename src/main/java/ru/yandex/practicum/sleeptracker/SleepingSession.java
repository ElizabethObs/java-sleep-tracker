package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SleepingSession {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final SleepQuality quality;

    public SleepingSession(LocalDateTime start, LocalDateTime end, SleepQuality quality) {
        this.start = start;
        this.end = end;
        this.quality = quality;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public SleepQuality getQuality() {
        return quality;
    }

    public long getDurationInMinutes() {
        return Duration.between(start, end).toMinutes();
    }

    public boolean isNightSleep() {
        LocalTime endTime = end.toLocalTime();
        boolean overnightSleep = !start.toLocalDate().equals(end.toLocalDate());
        boolean afterMidnightSleep = endTime.isBefore(LocalTime.of(6, 0));
        return overnightSleep || afterMidnightSleep;
    }

    public SleepChronotype getChronotype() {
        LocalTime startTime = start.toLocalTime();
        LocalTime endTime = end.toLocalTime();

        if (startTime.isAfter(LocalTime.of(23, 0)) && endTime.isAfter(LocalTime.of(9, 0))) {
            return SleepChronotype.OWL;
        }
        if (startTime.isBefore(LocalTime.of(22, 0)) && endTime.isBefore(LocalTime.of(7, 0))) {
            return SleepChronotype.LARK;
        }
        return SleepChronotype.DOVE;
    }
}
