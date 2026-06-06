package com.mindskip.xzs.domain;

import com.mindskip.xzs.domain.service.ScoringDomainService;
import com.mindskip.xzs.domain.service.ScoringDomainService.QuestionAnswerPair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class ScoringDomainServiceTest {

    private final ScoringDomainService service = new ScoringDomainService();

    @Test
    void shouldCalculateScoreForCorrectAnswers() {
        List<QuestionAnswerPair> answers = List.of(
                new QuestionAnswerPair(1, "A", "A", 10),
                new QuestionAnswerPair(1, "B", "B", 10)
        );
        assertEquals(20, service.calculateScore(answers));
    }

    @Test
    void shouldReturnZeroForAllWrongAnswers() {
        List<QuestionAnswerPair> answers = List.of(
                new QuestionAnswerPair(1, "A", "B", 10),
                new QuestionAnswerPair(2, "C", "D", 15)
        );
        assertEquals(0, service.calculateScore(answers));
    }

    @Test
    void shouldScorePartialCorrect() {
        List<QuestionAnswerPair> answers = List.of(
                new QuestionAnswerPair(1, "A", "A", 10),
                new QuestionAnswerPair(2, "C", "D", 15)
        );
        assertEquals(10, service.calculateScore(answers));
    }

    @Test
    void shouldHandleNullAnswers() {
        assertEquals(0, service.calculateScore(null));
    }

    @Test
    void shouldHandleEmptyAnswers() {
        assertEquals(0, service.calculateScore(List.of()));
    }

    @Test
    void shouldBeCaseSensitiveForSingleChoice() {
        List<QuestionAnswerPair> answers = List.of(
                new QuestionAnswerPair(1, "A", "a", 10)
        );
        assertEquals(0, service.calculateScore(answers));
    }

    @Test
    void shouldExactMatchForMultipleChoice() {
        List<QuestionAnswerPair> answers = List.of(
                new QuestionAnswerPair(2, "ABC", "ABC", 20)
        );
        assertEquals(20, service.calculateScore(answers));
    }

    @Test
    void shouldNotMatchDifferentOrderForMultipleChoice() {
        List<QuestionAnswerPair> answers = List.of(
                new QuestionAnswerPair(2, "ABC", "CBA", 20)
        );
        assertEquals(0, service.calculateScore(answers));
    }

    @Test
    void shouldCountCorrectAnswers() {
        List<QuestionAnswerPair> answers = List.of(
                new QuestionAnswerPair(1, "A", "A", 10),
                new QuestionAnswerPair(1, "B", "C", 10),
                new QuestionAnswerPair(2, "ABC", "ABC", 20)
        );
        assertEquals(2, service.calculateCorrectCount(answers));
    }

    @Test
    void shouldReturnZeroCorrectCountForNull() {
        assertEquals(0, service.calculateCorrectCount(null));
    }

    @Test
    void shouldNotAutoScoreGapFilling() {
        ScoringDomainService.ScoringResult result = service.score(4, "correct", "student_answer", 10);
        assertFalse(result.isAutoScorable());
        assertFalse(result.isCorrect());
        assertEquals(0, result.getScore());
    }

    @Test
    void shouldNotAutoScoreShortAnswer() {
        ScoringDomainService.ScoringResult result = service.score(5, "correct", "student_answer", 10);
        assertFalse(result.isAutoScorable());
        assertFalse(result.isCorrect());
        assertEquals(0, result.getScore());
    }

    @Test
    void shouldScoreTrueFalseCaseSensitive() {
        assertEquals(10, service.calculateScore(List.of(
                new QuestionAnswerPair(3, "true", "true", 10)
        )));
        assertEquals(0, service.calculateScore(List.of(
                new QuestionAnswerPair(3, "true", "True", 10)
        )));
    }
}
