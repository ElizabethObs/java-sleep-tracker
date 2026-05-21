package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class BadSleepQualityAnalysis implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long badSleepCount = sessions.stream().filter(session ->
                session.getQuality() == SleepQuality.BAD).count();
        return new SleepAnalysisResult("Количество плохих сессий сна", badSleepCount);
    }
}
