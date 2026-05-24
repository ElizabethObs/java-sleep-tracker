package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

public class SleeplessNightsAnalysis implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult("Количество бессонных ночей", 0);
        }
        SleepingSession firstSession = sessions.get(0);
        SleepingSession lastSession = sessions.get(sessions.size() - 1);
        LocalDate startDate = firstSession.getStart().toLocalDate();
        //Также будем считать, что если первая сессия сна в файле началась после 12 дня, потенциальной ночью для сна
        // считается следующая ночь, а если до 12 — то предыдущая.
        if (firstSession.getStart().getHour() >= 12) {
            startDate = startDate.plusDays(1);
        }
        LocalDate endDate = lastSession.getEnd().toLocalDate();

        long totalNights = startDate
                .datesUntil(endDate.plusDays(1))
                .count();

        long nightsWithSleep = sessions.stream()
                .filter(SleepingSession::isNightSleep)
                .count();
        long sleeplessNights = totalNights - nightsWithSleep;
        int result = (int) Math.max(0, sleeplessNights);

        return new SleepAnalysisResult("Количество бессонных ночей: ", result);
    }
}