package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class MinSleepDurationAnalysis implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long minDuration = sessions.stream()
                .mapToLong(session -> session.getDurationInMinutes())
                .min()
                .orElse(0);
        return new SleepAnalysisResult("Минимальная длительность сна", minDuration);
    }
}
