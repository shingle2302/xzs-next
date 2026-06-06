package com.mindskip.xzs.domain.gateway;

import com.mindskip.xzs.domain.aggregate.exam.ExamPaperAnswer;

import java.util.List;

public interface ExamPaperAnswerGateway {
    ExamPaperAnswer findById(Integer id);
    List<ExamPaperAnswer> findByUserId(Integer userId);
    List<ExamPaperAnswer> findPage(Integer pageIndex, Integer pageSize, Integer userId, Integer subjectId);
    long count(Integer userId, Integer subjectId);
    ExamPaperAnswer findByPaperAndUser(Integer examPaperId, Integer userId);
    void save(ExamPaperAnswer answer);
    void update(ExamPaperAnswer answer);
}
