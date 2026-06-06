package com.mindskip.xzs.adapter.dto.student.exam;

import com.mindskip.xzs.adapter.dto.common.PageRequest;

public class ExamPaperAnswerPageRequest extends PageRequest {
    private Integer subjectId;

    public Integer getSubjectId() { return subjectId; }
    public void setSubjectId(Integer subjectId) { this.subjectId = subjectId; }
}
