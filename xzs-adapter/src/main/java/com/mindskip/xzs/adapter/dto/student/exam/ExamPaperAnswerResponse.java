package com.mindskip.xzs.adapter.dto.student.exam;

import java.util.List;

public class ExamPaperAnswerResponse {
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
    private String createTime;
    private List<ExamPaperQuestionResponse> questionResponses;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getExamPaperId() { return examPaperId; }
    public void setExamPaperId(Integer examPaperId) { this.examPaperId = examPaperId; }
    public String getPaperName() { return paperName; }
    public void setPaperName(String paperName) { this.paperName = paperName; }
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
    public String getCreateTime() { return createTime; }
    public void setCreateTime(String createTime) { this.createTime = createTime; }
    public List<ExamPaperQuestionResponse> getQuestionResponses() { return questionResponses; }
    public void setQuestionResponses(List<ExamPaperQuestionResponse> questionResponses) { this.questionResponses = questionResponses; }

    public static class ExamPaperQuestionResponse {
        private Integer id;
        private Integer questionType;
        private String title;
        private Integer score;
        private String correctAnswer;
        private String answer;
        private Boolean doRight;
        private Integer itemOrder;

        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
        public Integer getQuestionType() { return questionType; }
        public void setQuestionType(Integer questionType) { this.questionType = questionType; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public Integer getScore() { return score; }
        public void setScore(Integer score) { this.score = score; }
        public String getCorrectAnswer() { return correctAnswer; }
        public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }
        public String getAnswer() { return answer; }
        public void setAnswer(String answer) { this.answer = answer; }
        public Boolean getDoRight() { return doRight; }
        public void setDoRight(Boolean doRight) { this.doRight = doRight; }
        public Integer getItemOrder() { return itemOrder; }
        public void setItemOrder(Integer itemOrder) { this.itemOrder = itemOrder; }
    }
}
