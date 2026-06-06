package com.mindskip.xzs.domain.gateway;

import com.mindskip.xzs.domain.aggregate.exam.ExamPaper;

import java.util.List;

public interface ExamPaperGateway {
    ExamPaper findById(Integer id);
    List<ExamPaper> findPage(Integer pageIndex, Integer pageSize, Integer subjectId, Integer paperType);
    long count(Integer subjectId, Integer paperType);
    List<ExamPaper> findStudentPage(Integer pageIndex, Integer pageSize, Integer gradeLevel, Integer subjectId);
    List<ExamPaper> findByTaskExamId(Integer taskExamId);
    long countAll();
    void save(ExamPaper examPaper);
    void update(ExamPaper examPaper);
    void softDelete(Integer id);
}
