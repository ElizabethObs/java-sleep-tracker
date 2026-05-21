package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepTrackerAppTest {
    // тесты для CountSleepSessionsAnalysis
    @Test
    public void shouldCountAllSessionsTest() {
        SleepingSession session1 = new SleepingSession(LocalDateTime.now(), LocalDateTime.now(),
                SleepQuality.GOOD);
        SleepingSession session2 = new SleepingSession(LocalDateTime.now(), LocalDateTime.now(),
                SleepQuality.NORMAL);
        SleepingSession session3 = new SleepingSession(LocalDateTime.now(), LocalDateTime.now(),
                SleepQuality.BAD);
        List<SleepingSession> sessions = List.of(session1, session2, session3);
        CountSleepSessionsAnalysis analysis = new CountSleepSessionsAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(3, result.getValue());
    }

    @Test
    public void shouldReturnZeroForEmptyListTest1() {
        List<SleepingSession> sessions = List.of();
        CountSleepSessionsAnalysis analysis = new CountSleepSessionsAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(0L, result.getValue());
    }

    // тесты для MinSleepDurationAnalysis()
    @Test
    public void shouldFindMinDurationTest() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2026, 10, 1, 22, 0),
                LocalDateTime.of(2026, 10, 2, 6, 0), SleepQuality.GOOD);
        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2026, 10, 2, 13, 0),
                LocalDateTime.of(2026, 10, 2, 16, 0), SleepQuality.NORMAL);
        List<SleepingSession> sessions = List.of(session1, session2);
        MinSleepDurationAnalysis analysis = new MinSleepDurationAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(180, result.getValue());
    }

    @Test
    public void shouldReturnZeroForEmptyListTest2() {
        List<SleepingSession> sessions = List.of();
        MinSleepDurationAnalysis analysis = new MinSleepDurationAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(0L, result.getValue());
    }

    // тесты для MaxSleepDurationAnalysis()
    @Test
    public void shouldFindMaxDurationTest() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2026, 10, 1, 10, 0),
                LocalDateTime.of(2026, 10, 1, 14, 0), SleepQuality.NORMAL);
        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2026, 10, 1, 22, 0),
                LocalDateTime.of(2026, 10, 2, 8, 0), SleepQuality.GOOD);
        List<SleepingSession> sessions = List.of(session1, session2);
        MaxSleepDurationAnalysis analysis = new MaxSleepDurationAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(600L, result.getValue());
    }

    @Test
    public void shouldReturnZeroForEmptyListTest3() {
        List<SleepingSession> sessions = List.of();
        MaxSleepDurationAnalysis analysis = new MaxSleepDurationAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(0L, result.getValue());
    }

    // тесты для AverageSleepDurationAnalysis()
    @Test
    public void shouldCalculateAverageDurationTest() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2026, 12, 1, 10, 0),
                LocalDateTime.of(2026, 12, 1, 12, 0), SleepQuality.BAD);//120 минут
        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2026, 12, 2, 4, 0),
                LocalDateTime.of(2026, 12, 2, 8, 0), SleepQuality.NORMAL);//240 минут
        List<SleepingSession> sessions = List.of(session1, session2);
        AverageSleepDurationAnalysis analysis = new AverageSleepDurationAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        // 120 + 240 = 360 / 2 = 180 минут
        assertEquals(180, result.getValue());
    }

    @Test
    public void shouldReturnZeroForEmptyListTest4() {
        List<SleepingSession> sessions = List.of();
        AverageSleepDurationAnalysis analysis = new AverageSleepDurationAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(0.0, result.getValue());
    }

    // тесты для BadSleepQualityAnalysis()
    @Test
    public void shouldCountBadSleepSessionsTest() {
        SleepingSession session1 = new SleepingSession(LocalDateTime.now(), LocalDateTime.now(), SleepQuality.BAD);
        SleepingSession session2 = new SleepingSession(LocalDateTime.now(), LocalDateTime.now(), SleepQuality.BAD);
        SleepingSession session3 = new SleepingSession(LocalDateTime.now(), LocalDateTime.now(), SleepQuality.GOOD);
        List<SleepingSession> sessions = List.of(session1, session2, session3);
        BadSleepQualityAnalysis analysis = new BadSleepQualityAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(2L, result.getValue());
    }

    @Test
    public void shouldReturnZeroWhenNoBadSessionsTest() {
        SleepingSession session = new SleepingSession(LocalDateTime.now(), LocalDateTime.now(), SleepQuality.GOOD);
        List<SleepingSession> sessions = List.of(session);
        BadSleepQualityAnalysis analysis = new BadSleepQualityAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(0L, result.getValue());
    }

    // тесты для SleeplessNightsAnalysis
    @Test
    public void shouldReturnZeroSleeplessNightsTest() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2026, 5, 1, 23, 0),
                LocalDateTime.of(2026, 5, 2, 7, 0), SleepQuality.GOOD);
        List<SleepingSession> sessions = List.of(session);
        SleeplessNightsAnalysis analysis = new SleeplessNightsAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(0L, result.getValue());
    }

    @Test
    public void shouldCountSleeplessNightTest() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2026, 11, 1, 10, 0),
                LocalDateTime.of(2026, 11, 1, 13, 0), SleepQuality.NORMAL);
        List<SleepingSession> sessions = List.of(session);
        SleeplessNightsAnalysis analysis = new SleeplessNightsAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(1L, result.getValue());
    }

    @Test
    public void shouldCountNightSleepAfterMidnightTest() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2026, 9, 7, 2, 0),
                LocalDateTime.of(2026, 9, 7, 5, 0), SleepQuality.NORMAL);
        List<SleepingSession> sessions = List.of(session);
        SleeplessNightsAnalysis analysis = new SleeplessNightsAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(0L, result.getValue());
    }

    @Test
    public void shouldReturnZeroForEmptyListTest5() {
        List<SleepingSession> sessions = List.of();
        SleeplessNightsAnalysis analysis = new SleeplessNightsAnalysis();
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(0, result.getValue());
    }

    //тесты для SleepChronotypeAnalysis
    private final SleepChronotypeAnalysis analysis = new SleepChronotypeAnalysis();

    @Test
    public void shouldReturnOWLTest() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2026, 1, 7, 23, 30),
                LocalDateTime.of(2026, 1, 8, 9, 0), SleepQuality.GOOD);
        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2026, 9, 9, 23, 50),
                LocalDateTime.of(2026, 9, 10, 10, 0), SleepQuality.GOOD);
        List<SleepingSession> sessions = List.of(session1, session2);
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(SleepChronotype.OWL, result.getValue());
    }

    @Test
    public void shouldReturnLARKTest() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2026, 1, 7, 21, 0),
                LocalDateTime.of(2026, 1, 8, 6, 0), SleepQuality.GOOD);
        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2026, 9, 9, 21, 30),
                LocalDateTime.of(2026, 9, 10, 6, 40), SleepQuality.GOOD);
        List<SleepingSession> sessions = List.of(session1, session2);
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(SleepChronotype.LARK, result.getValue());
    }

    @Test
    public void shouldReturnDOVETest() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2026, 1, 7, 23, 40),
                LocalDateTime.of(2026, 1, 8, 11, 0), SleepQuality.GOOD);
        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2026, 9, 9, 21, 30),
                LocalDateTime.of(2026, 9, 10, 6, 30), SleepQuality.GOOD);
        List<SleepingSession> sessions = List.of(session1, session2);
        SleepAnalysisResult result = analysis.apply(sessions);
        assertEquals(SleepChronotype.DOVE, result.getValue());

    }
}