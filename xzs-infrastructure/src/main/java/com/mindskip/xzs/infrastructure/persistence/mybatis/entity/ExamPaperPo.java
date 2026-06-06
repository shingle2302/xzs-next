package com.mindskip.xzs.infrastructure.persistence.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

@TableName("exam_paper")
public class ExamPaperPo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer subjectId;
    private Integer paperType;
    private Integer gradeLevel;
    private Integer score;
    private Integer questionCount;
    private Integer suggestTime;
    private LocalDateTime limitStartTime;
    private LocalDateTime limitEndTime;
    private Integer frameTextContentId;
    private Integer createUser;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableLogic
    private Boolean deleted;
    private Integer taskExamId;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getSubjectId() { return subjectId; }
    public void setSubjectId(Integer subjectId) { this.subjectId = subjectId; }
    public Integer getPaperType() { return paperType; }
    public void setPaperType(Integer paperType) { this.paperType = paperType; }
    public Integer getGradeLevel() { return gradeLevel; }
    public void setGradeLevel(Integer gradeLevel) { this.gradeLevel = gradeLevel; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public Integer getQuestionCount() { return questionCount; }
    public void setQuestionCount(Integer questionCount) { this.questionCount = questionCount; }
    public Integer getSuggestTime() { return suggestTime; }
    public void setSuggestTime(Integer suggestTime) { this.suggestTime = suggestTime; }
    public LocalDateTime getLimitStartTime() { return limitStartTime; }
    public void setLimitStartTime(LocalDateTime limitStartTime) { this.limitStartTime = limitStartTime; }
    public LocalDateTime getLimitEndTime() { return limitEndTime; }
    public void setLimitEndTime(LocalDateTime limitEndTime) { this.limitEndTime = limitEndTime; }
    public Integer getFrameTextContentId() { return frameTextContentId; }
    public void setFrameTextContentId(Integer frameTextContentId) { this.frameTextContentId = frameTextContentId; }
    public Integer getCreateUser() { return createUser; }
    public void setCreateUser(Integer createUser) { this.createUser = createUser; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }
    public Integer getTaskExamId() { return taskExamId; }
    public void setTaskExamId(Integer taskExamId) { this.taskExamId = taskExamId; }
}
