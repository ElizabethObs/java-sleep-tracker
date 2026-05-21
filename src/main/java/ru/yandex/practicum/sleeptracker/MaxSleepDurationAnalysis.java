package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class MaxSleepDurationAnalysis implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long maxDuration = sessions.stream()
                .mapToLong(session -> session.getDurationInMinutes())
                .max()
                .orElse(0);
        return new SleepAnalysisResult("Максимальная длительность сна", maxDuration);
    }
}

