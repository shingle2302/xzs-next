package com.mindskip.xzs.domain.service;

import com.mindskip.xzs.enums.QuestionTypeEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ScoringDomainService {

    public static class ScoringResult {
        private final boolean autoScorable;
        private final boolean correct;
        private final int score;

        public ScoringResult(boolean autoScorable, boolean correct, int score) {
            this.autoScorable = autoScorable;
            this.correct = correct;
            this.score = score;
        }

        public boolean isAutoScorable() { return autoScorable; }
        public boolean isCorrect() { return correct; }
        public int getScore() { return score; }
    }

    public ScoringResult score(Integer questionType, String correctAnswer, String studentAnswer, int questionScore) {
        if (questionType == null) return new ScoringResult(true, false, 0);
        QuestionTypeEnum type = QuestionTypeEnum.fromCode(questionType);
        if (type == null) return new ScoringResult(true, false, 0);

        switch (type) {
            case SingleChoice:
            case TrueFalse:
                return scoreSingleChoice(correctAnswer, studentAnswer, questionScore);
            case MultipleChoice:
                return scoreMultipleChoice(correctAnswer, studentAnswer, questionScore);
            case GapFilling:
            case ShortAnswer:
                return new ScoringResult(false, false, 0);
            default:
                return new ScoringResult(true, false, 0);
        }
    }

    private ScoringResult scoreSingleChoice(String correctAnswer, String studentAnswer, int questionScore) {
        if (correctAnswer == null || studentAnswer == null) {
            return new ScoringResult(true, false, 0);
        }
        boolean correct = correctAnswer.equals(studentAnswer);
        return new ScoringResult(true, correct, correct ? questionScore : 0);
    }

    private ScoringResult scoreMultipleChoice(String correctAnswer, String studentAnswer, int questionScore) {
        if (correctAnswer == null || studentAnswer == null) {
            return new ScoringResult(true, false, 0);
        }
        boolean correct = correctAnswer.equals(studentAnswer);
        return new ScoringResult(true, correct, correct ? questionScore : 0);
    }

    public int calculateScore(List<QuestionAnswerPair> answers) {
        if (answers == null) return 0;
        return answers.stream()
                .filter(a -> {
                    ScoringResult r = score(a.questionType, a.correctAnswer, a.studentAnswer, a.score);
                    return r.isAutoScorable() && r.isCorrect();
                })
                .mapToInt(a -> a.score)
                .sum();
    }

    public int calculateCorrectCount(List<QuestionAnswerPair> answers) {
        if (answers == null) return 0;
        return (int) answers.stream()
                .filter(a -> {
                    ScoringResult r = score(a.questionType, a.correctAnswer, a.studentAnswer, a.score);
                    return r.isAutoScorable() && r.isCorrect();
                })
                .count();
    }

    public static class QuestionAnswerPair {
        private final Integer questionType;
        private final String correctAnswer;
        private final String studentAnswer;
        private final int score;

        public QuestionAnswerPair(Integer questionType, String correctAnswer, String studentAnswer, int score) {
            this.questionType = questionType;
            this.correctAnswer = correctAnswer;
            this.studentAnswer = studentAnswer;
            this.score = score;
        }
    }
}
