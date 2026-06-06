package com.mindskip.xzs.domain.event;

public class ExamSubmittedEvent {
    private final Integer examPaperAnswerId;
    private final Integer examPaperId;
    private final Integer userId;

    public ExamSubmittedEvent(Integer examPaperAnswerId, Integer examPaperId, Integer userId) {
        this.examPaperAnswerId = examPaperAnswerId;
        this.examPaperId = examPaperId;
        this.userId = userId;
    }

    public Integer getExamPaperAnswerId() { return examPaperAnswerId; }
    public Integer getExamPaperId() { return examPaperId; }
    public Integer getUserId() { return userId; }
}
