package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class SleepChronotypeAnalysis implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        List<SleepingSession> nightSessions = sessions.stream()
                .filter(session -> session.isNightSleep())
                .toList();
        long owl = nightSessions.stream().filter(s -> s.getChronotype() == SleepChronotype.OWL).count();
        long lark = nightSessions.stream().filter(s -> s.getChronotype() == SleepChronotype.LARK).count();
        long dove = nightSessions.stream().filter(s -> s.getChronotype() == SleepChronotype.DOVE).count();

        SleepChronotype resultChronotype;
        if (owl > lark && owl > dove) {
            resultChronotype = SleepChronotype.OWL;
        } else if (lark > owl && lark > dove) {
            resultChronotype = SleepChronotype.LARK;
        } else {
            resultChronotype = SleepChronotype.DOVE;
        }
        return new SleepAnalysisResult("Хронотип пользователя", resultChronotype);
    }
}
