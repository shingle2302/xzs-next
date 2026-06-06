package com.mindskip.xzs.domain.aggregate.question;

import java.time.LocalDateTime;

public class Question {
    private Integer id;
    private Integer questionType;
    private Integer subjectId;
    private Integer score;
    private Integer gradeLevel;
    private Integer difficult;
    private String correctAnswer;
    private Integer analyzeTextContentId;
    private Integer infoTextContentId;
    private Integer createUser;
    private Integer status;
    private LocalDateTime createTime;
    private Boolean deleted;

    public Question() {}

    public Question(Integer questionType, Integer subjectId, Integer score, Integer createUser) {
        this.questionType = questionType;
        this.subjectId = subjectId;
        this.score = score;
        this.createUser = createUser;
        this.status = 1;
        this.deleted = false;
        this.createTime = LocalDateTime.now();
    }

    public boolean isSingleChoice() { return questionType != null && questionType == 1; }
    public boolean isMultipleChoice() { return questionType != null && questionType == 2; }
    public boolean isTrueFalse() { return questionType != null && questionType == 3; }
    public boolean needTextContent() {
        return questionType != null && (questionType == 4 || questionType == 5);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getQuestionType() { return questionType; }
    public void setQuestionType(Integer questionType) { this.questionType = questionType; }
    public Integer getSubjectId() { return subjectId; }
    public void setSubjectId(Integer subjectId) { this.subjectId = subjectId; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public Integer getGradeLevel() { return gradeLevel; }
    public void setGradeLevel(Integer gradeLevel) { this.gradeLevel = gradeLevel; }
    public Integer getDifficult() { return difficult; }
    public void setDifficult(Integer difficult) { this.difficult = difficult; }
    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }
    public Integer getAnalyzeTextContentId() { return analyzeTextContentId; }
    public void setAnalyzeTextContentId(Integer analyzeTextContentId) { this.analyzeTextContentId = analyzeTextContentId; }
    public Integer getInfoTextContentId() { return infoTextContentId; }
    public void setInfoTextContentId(Integer infoTextContentId) { this.infoTextContentId = infoTextContentId; }
    public Integer getCreateUser() { return createUser; }
    public void setCreateUser(Integer createUser) { this.createUser = createUser; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }
}
