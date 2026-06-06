package com.mindskip.xzs.domain.gateway;

import com.mindskip.xzs.domain.aggregate.exam.ExamPaperQuestionCustomerAnswer;

import java.util.List;

public interface ExamPaperQuestionCustomerAnswerGateway {
    List<ExamPaperQuestionCustomerAnswer> findByAnswerId(Integer examPaperAnswerId);
    List<ExamPaperQuestionCustomerAnswer> findByUserId(Integer userId);
    void save(ExamPaperQuestionCustomerAnswer answer);
    void saveBatch(List<ExamPaperQuestionCustomerAnswer> answers);
    void update(ExamPaperQuestionCustomerAnswer answer);
}
