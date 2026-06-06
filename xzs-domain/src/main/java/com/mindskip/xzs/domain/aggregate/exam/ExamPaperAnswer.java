package com.mindskip.xzs.domain.aggregate.exam;

import java.time.LocalDateTime;

public class ExamPaperAnswer {
    private Integer id;
    private Integer examPaperId;
    private String paperName;
    private Integer paperType;
    private Integer subjectId;
    private Integer userId;
    private String userName;
    private Integer paperScore;
    private Integer questionCorrect;
    private Integer questionCount;
    private Integer systemScore;
    private Integer userScore;
    private Integer status;
    private Integer taskExamId;
    private Integer doTime;
    private LocalDateTime createTime;

    public ExamPaperAnswer() {}

    public ExamPaperAnswer(Integer examPaperId, Integer userId, Integer status) {
        this.examPaperId = examPaperId;
        this.userId = userId;
        this.status = status;
        this.createTime = LocalDateTime.now();
    }

    public void complete(int systemScore) {
        this.systemScore = systemScore;
        this.status = 2;
    }

    public boolean isComplete() {
        return status != null && status == 2;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getExamPaperId() { return examPaperId; }
    public void setExamPaperId(Integer examPaperId) { this.examPaperId = examPaperId; }
    public String getPaperName() { return paperName; }
    public void setPaperName(String paperName) { this.paperName = paperName; }
    public Integer getDoTime() { return doTime; }
    public void setDoTime(Integer doTime) { this.doTime = doTime; }
    public Integer getPaperType() { return paperType; }
    public void setPaperType(Integer paperType) { this.paperType = paperType; }
    public Integer getSubjectId() { return subjectId; }
    public void setSubjectId(Integer subjectId) { this.subjectId = subjectId; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public Integer getPaperScore() { return paperScore; }
    public void setPaperScore(Integer paperScore) { this.paperScore = paperScore; }
    public Integer getQuestionCorrect() { return questionCorrect; }
    public void setQuestionCorrect(Integer questionCorrect) { this.questionCorrect = questionCorrect; }
    public Integer getQuestionCount() { return questionCount; }
    public void setQuestionCount(Integer questionCount) { this.questionCount = questionCount; }
    public Integer getSystemScore() { return systemScore; }
    public void setSystemScore(Integer systemScore) { this.systemScore = systemScore; }
    public Integer getUserScore() { return userScore; }
    public void setUserScore(Integer userScore) { this.userScore = userScore; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Integer getTaskExamId() { return taskExamId; }
    public void setTaskExamId(Integer taskExamId) { this.taskExamId = taskExamId; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
}
