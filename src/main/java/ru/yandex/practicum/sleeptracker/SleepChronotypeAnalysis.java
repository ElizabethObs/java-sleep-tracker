package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class SleepChronotypeAnalysis implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long owl = 0;
        long lark = 0;
        long dove = 0;

        for (SleepingSession session : sessions) {

            SleepChronotype type = classify(session);

            if (type == SleepChronotype.OWL) {
                owl++;
            } else if (type == SleepChronotype.LARK) {
                lark++;
            } else {
                dove++;
            }
        }
        SleepChronotype result;

        if (owl > lark && owl > dove) {
            result = SleepChronotype.OWL;
        } else if (lark > owl && lark > dove) {
            result = SleepChronotype.LARK;
        } else {
            result = SleepChronotype.DOVE;
        }
        return new SleepAnalysisResult("Хронотип пользователя: ", result);
    }

        private SleepChronotype classify(SleepingSession session) {
            LocalTime start = session.getStart().toLocalTime();
            LocalTime end = session.getEnd().toLocalTime();
            if (start.getHour() >= 23 && end.getHour() >= 9) {
                return SleepChronotype.OWL;
            }
            if (start.getHour() <= 21 && end.getHour() <= 7) {
                return SleepChronotype.LARK;
            }
            return SleepChronotype.DOVE;
        }
}