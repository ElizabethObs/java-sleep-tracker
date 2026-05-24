package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class AverageSleepDurationAnalysis implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        int averageDuration = (int) sessions.stream()
                .mapToLong(session -> session.getDurationInMinutes())
                .average()
                .orElse(0);
        return new SleepAnalysisResult("Средняя длительность сна: ", averageDuration);
    }
}
