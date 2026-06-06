package com.mindskip.xzs.domain.aggregate.task;

import java.time.LocalDateTime;

public class TaskExam {
    private Integer id;
    private String title;
    private Integer gradeLevel;
    private Integer frameTextContentId;
    private Integer createUser;
    private LocalDateTime createTime;
    private Boolean deleted;

    public TaskExam() {}

    public TaskExam(String title, Integer gradeLevel, Integer createUser) {
        this.title = title;
        this.gradeLevel = gradeLevel;
        this.createUser = createUser;
        this.createTime = LocalDateTime.now();
        this.deleted = false;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Integer getGradeLevel() { return gradeLevel; }
    public void setGradeLevel(Integer gradeLevel) { this.gradeLevel = gradeLevel; }
    public Integer getFrameTextContentId() { return frameTextContentId; }
    public void setFrameTextContentId(Integer frameTextContentId) { this.frameTextContentId = frameTextContentId; }
    public Integer getCreateUser() { return createUser; }
    public void setCreateUser(Integer createUser) { this.createUser = createUser; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }
}
