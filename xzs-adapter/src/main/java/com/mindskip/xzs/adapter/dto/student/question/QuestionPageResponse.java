package com.mindskip.xzs.adapter.dto.student.question;

import java.util.List;

public class QuestionPageResponse {
    private Integer id;
    private Integer questionType;
    private String title;
    private Integer score;
    private String createTime;
    private Boolean doRight;
    private List<QuestionItemResponse> items;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getQuestionType() { return questionType; }
    public void setQuestionType(Integer questionType) { this.questionType = questionType; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public String getCreateTime() { return createTime; }
    public void setCreateTime(String createTime) { this.createTime = createTime; }
    public Boolean getDoRight() { return doRight; }
    public void setDoRight(Boolean doRight) { this.doRight = doRight; }
    public List<QuestionItemResponse> getItems() { return items; }
    public void setItems(List<QuestionItemResponse> items) { this.items = items; }

    public static class QuestionItemResponse {
        private String prefix;
        private String content;

        public String getPrefix() { return prefix; }
        public void setPrefix(String prefix) { this.prefix = prefix; }
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
    }
}
