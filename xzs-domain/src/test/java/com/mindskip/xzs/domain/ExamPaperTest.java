package com.mindskip.xzs.domain;

import com.mindskip.xzs.domain.aggregate.exam.ExamPaper;
import com.mindskip.xzs.domain.service.ExamPaperDomainService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class ExamPaperTest {

    private final ExamPaperDomainService domainService = new ExamPaperDomainService();

    @Test
    void shouldRejectPaperWithBlankName() {
        ExamPaper paper = new ExamPaper("Test", 1, 1, 1);
        paper.setName(" ");
        assertThrows(IllegalArgumentException.class, () -> domainService.validateBeforeSave(paper));
    }

    @Test
    void shouldRejectPaperWithoutSubject() {
        ExamPaper paper = new ExamPaper("Test", 1, 1, 1);
        paper.setSubjectId(null);
        assertThrows(IllegalArgumentException.class, () -> domainService.validateBeforeSave(paper));
    }

    @Test
    void shouldRejectPaperWithoutPaperType() {
        ExamPaper paper = new ExamPaper("Test", 1, 1, 1);
        paper.setPaperType(null);
        assertThrows(IllegalArgumentException.class, () -> domainService.validateBeforeSave(paper));
    }

    @Test
    void shouldPassValidationWithValidPaper() {
        ExamPaper paper = new ExamPaper("Test", 1, 1, 1);
        assertDoesNotThrow(() -> domainService.validateBeforeSave(paper));
    }

    @Test
    void shouldAllowStudentAccessForSameGrade() {
        ExamPaper paper = new ExamPaper("Test", 1, 1, 1);
        paper.setGradeLevel(2);
        assertTrue(domainService.canStudentAccess(paper, 2));
    }

    @Test
    void shouldDenyAccessForDifferentGrade() {
        ExamPaper paper = new ExamPaper("Test", 1, 1, 1);
        paper.setGradeLevel(2);
        assertFalse(domainService.canStudentAccess(paper, 3));
    }

    @Test
    void shouldDenyAccessForDeletedPaper() {
        ExamPaper paper = new ExamPaper("Test", 1, 1, 1);
        paper.setDeleted(true);
        assertFalse(domainService.canStudentAccess(paper, 1));
    }

    @Test
    void shouldAllowAccessForNullGradeLevel() {
        ExamPaper paper = new ExamPaper("Test", 1, 1, 1);
        paper.setGradeLevel(null);
        assertTrue(domainService.canStudentAccess(paper, 1));
    }

    @Test
    void shouldBeTimeValidForNonTimedPaper() {
        ExamPaper paper = new ExamPaper("Test", 1, 1, 1);
        assertTrue(paper.isTimeLimitValid());
    }

    @Test
    void shouldBeTimeValidWithinLimit() {
        ExamPaper paper = new ExamPaper("Test", 1, 4, 1);
        paper.setLimitStartTime(LocalDateTime.now().minusHours(1));
        paper.setLimitEndTime(LocalDateTime.now().plusHours(1));
        assertTrue(paper.isTimeLimitValid());
    }

    @Test
    void shouldBeTimeInvalidBeforeStart() {
        ExamPaper paper = new ExamPaper("Test", 1, 4, 1);
        paper.setLimitStartTime(LocalDateTime.now().plusHours(1));
        paper.setLimitEndTime(LocalDateTime.now().plusHours(2));
        assertFalse(paper.isTimeLimitValid());
    }

    @Test
    void shouldMarkDeleted() {
        ExamPaper paper = new ExamPaper("Test", 1, 1, 1);
        paper.markDeleted();
        assertTrue(paper.getDeleted());
    }

    @Test
    void shouldUpdateScore() {
        ExamPaper paper = new ExamPaper("Test", 1, 1, 1);
        paper.updateScore(100, 10);
        assertEquals(100, paper.getScore());
        assertEquals(10, paper.getQuestionCount());
    }
}
