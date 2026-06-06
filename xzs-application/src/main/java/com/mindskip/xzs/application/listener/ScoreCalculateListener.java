package com.mindskip.xzs.application.listener;

import com.mindskip.xzs.domain.aggregate.exam.ExamPaperAnswer;
import com.mindskip.xzs.domain.event.ExamSubmittedEvent;
import com.mindskip.xzs.domain.gateway.ExamPaperAnswerGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ScoreCalculateListener {
    private static final Logger log = LoggerFactory.getLogger(ScoreCalculateListener.class);

    private final ExamPaperAnswerGateway answerGateway;

    public ScoreCalculateListener(ExamPaperAnswerGateway answerGateway) {
        this.answerGateway = answerGateway;
    }

    @EventListener
    public void handleExamSubmitted(ExamSubmittedEvent event) {
        ExamPaperAnswer answer = answerGateway.findById(event.getExamPaperAnswerId());
        if (answer == null) {
            log.warn("答卷已提交但未找到: answerId={}", event.getExamPaperAnswerId());
            return;
        }
        log.info("答卷自动评分完成: answerId={}, paperId={}, userId={}, systemScore={}, status={}",
                event.getExamPaperAnswerId(), event.getExamPaperId(), event.getUserId(),
                answer.getSystemScore(), answer.getStatus());
    }
}
