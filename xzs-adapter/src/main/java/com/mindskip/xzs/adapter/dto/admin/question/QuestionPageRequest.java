package com.mindskip.xzs.adapter.dto.admin.question;

import com.mindskip.xzs.adapter.dto.common.PageRequest;

public class QuestionPageRequest extends PageRequest {
    private Integer subjectId;
    private Integer questionType;
    private Integer gradeLevel;

    public Integer getSubjectId() { return subjectId; }
    public void setSubjectId(Integer subjectId) { this.subjectId = subjectId; }
    public Integer getQuestionType() { return questionType; }
    public void setQuestionType(Integer questionType) { this.questionType = questionType; }
    public Integer getGradeLevel() { return gradeLevel; }
    public void setGradeLevel(Integer gradeLevel) { this.gradeLevel = gradeLevel; }
}
