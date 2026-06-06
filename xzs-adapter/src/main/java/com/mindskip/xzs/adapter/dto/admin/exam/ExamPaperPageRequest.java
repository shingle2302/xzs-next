package com.mindskip.xzs.adapter.dto.admin.exam;

import com.mindskip.xzs.adapter.dto.common.PageRequest;

public class ExamPaperPageRequest extends PageRequest {
    private Integer subjectId;
    private Integer paperType;

    public Integer getSubjectId() { return subjectId; }
    public void setSubjectId(Integer subjectId) { this.subjectId = subjectId; }
    public Integer getPaperType() { return paperType; }
    public void setPaperType(Integer paperType) { this.paperType = paperType; }
}
