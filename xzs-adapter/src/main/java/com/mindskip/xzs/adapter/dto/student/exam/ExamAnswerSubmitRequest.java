package com.mindskip.xzs.adapter.dto.student.exam;

import java.util.List;

public class ExamAnswerSubmitRequest {
    private Integer examPaperId;
    private Integer doTime;
    private List<AnswerItem> answerItems;

    public Integer getExamPaperId() { return examPaperId; }
    public void setExamPaperId(Integer examPaperId) { this.examPaperId = examPaperId; }
    public Integer getDoTime() { return doTime; }
    public void setDoTime(Integer doTime) { this.doTime = doTime; }
    public List<AnswerItem> getAnswerItems() { return answerItems; }
    public void setAnswerItems(List<AnswerItem> answerItems) { this.answerItems = answerItems; }

    public static class AnswerItem {
        private Integer id;
        private Integer questionId;
        private String content;
        private List<String> contentArray;
        private Boolean doRight;
        private Integer itemOrder;
        private String score;
        private String questionScore;

        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
        public Integer getQuestionId() { return questionId; }
        public void setQuestionId(Integer questionId) { this.questionId = questionId; }
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
        public List<String> getContentArray() { return contentArray; }
        public void setContentArray(List<String> contentArray) { this.contentArray = contentArray; }
        public Boolean getDoRight() { return doRight; }
        public void setDoRight(Boolean doRight) { this.doRight = doRight; }
        public Integer getItemOrder() { return itemOrder; }
        public void setItemOrder(Integer itemOrder) { this.itemOrder = itemOrder; }
        public String getScore() { return score; }
        public void setScore(String score) { this.score = score; }
        public String getQuestionScore() { return questionScore; }
        public void setQuestionScore(String questionScore) { this.questionScore = questionScore; }
    }
}
