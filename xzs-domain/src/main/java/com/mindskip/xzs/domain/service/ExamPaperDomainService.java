package com.mindskip.xzs.domain.service;

import com.mindskip.xzs.domain.aggregate.exam.ExamPaper;

public class ExamPaperDomainService {

    public void validateBeforeSave(ExamPaper examPaper) {
        if (examPaper.getName() == null || examPaper.getName().isBlank()) {
            throw new IllegalArgumentException("试卷名称不能为空");
        }
        if (examPaper.getSubjectId() == null) {
            throw new IllegalArgumentException("请选择学科");
        }
        if (examPaper.getPaperType() == null) {
            throw new IllegalArgumentException("请选择试卷类型");
        }
    }

    public boolean canStudentAccess(ExamPaper examPaper, Integer gradeLevel) {
        if (examPaper.getDeleted() != null && examPaper.getDeleted()) return false;
        if (examPaper.getGradeLevel() != null && gradeLevel != null
                && !examPaper.getGradeLevel().equals(gradeLevel)) return false;
        return examPaper.isTimeLimitValid();
    }
}
