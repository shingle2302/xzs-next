package com.mindskip.xzs.adapter.dto.student.question;

import com.mindskip.xzs.adapter.dto.common.PageRequest;

public class QuestionPageRequest extends PageRequest {
    private Integer questionType;
    private Integer subjectId;

    public Integer getQuestionType() { return questionType; }
    public void setQuestionType(Integer questionType) { this.questionType = questionType; }
    public Integer getSubjectId() { return subjectId; }
    public void setSubjectId(Integer subjectId) { this.subjectId = subjectId; }
}
