package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.Period;
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
        LocalDate endDate = lastSession.getEnd().toLocalDate();
        //Также будем считать, что если первая сессия сна в файле началась после 12 дня, потенциальной ночью для сна
        // считается следующая ночь, а если до 12 — то предыдущая.
        if (firstSession.getStart().getHour() >= 12) {
            startDate = startDate.plusDays(1);
        }
        long totalNights = Period.between(startDate, endDate).getDays();
        long nightsWithSleep = sessions.stream()
                .filter(session -> session.isNightSleep())
                .count();
        long sleeplessNights = totalNights - nightsWithSleep;

        if (sleeplessNights < 0) {
            sleeplessNights = 0;
        }
        return new SleepAnalysisResult("Количество бессонных ночей", sleeplessNights);
    }
}