package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class SleepTrackerApp {

    public static void main(String[] args) throws IOException {
        String filePath = "sleep_log.txt";
        List<SleepingSession> sessions = readSessions(filePath);
        System.out.println("Количество сессий: " + sessions.size());
        List<Function<List<SleepingSession>, SleepAnalysisResult>> analyses = List.of(
                new CountSleepSessionsAnalysis(),
                new MinSleepDurationAnalysis(),
                new MaxSleepDurationAnalysis(),
                new AverageSleepDurationAnalysis(),
                new BadSleepQualityAnalysis(),
                new SleeplessNightsAnalysis(),
                new SleepChronotypeAnalysis()
        );
        analyses.stream()
                .map(analysis -> analysis.apply(sessions))
                .forEach(result -> System.out.println(result.getDescription() + result.getValue())
        );
    }

    private static List<SleepingSession> readSessions(String path) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            return lines.map(line -> parseSession(line)).toList();
        }
    }

    private static SleepingSession parseSession(String line) {
        String[] parts = line.split(";");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        LocalDateTime start = LocalDateTime.parse(parts[0], formatter);
        LocalDateTime end = LocalDateTime.parse(parts[1], formatter);
        SleepQuality quality = SleepQuality.valueOf(parts[2]);
        return new SleepingSession(start, end, quality);
    }
}