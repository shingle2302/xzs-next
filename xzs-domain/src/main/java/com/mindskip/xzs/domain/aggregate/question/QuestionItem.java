package com.mindskip.xzs.domain.aggregate.question;

import java.util.Objects;

public class QuestionItem {
    private Integer id;
    private Integer questionId;
    private String prefix;
    private String content;
    private Integer score;
    private Integer itemOrder;

    public QuestionItem() {}

    public QuestionItem(Integer questionId, String prefix, String content, Integer itemOrder) {
        this.questionId = Objects.requireNonNull(questionId);
        this.prefix = prefix;
        this.content = content;
        this.itemOrder = itemOrder;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getQuestionId() { return questionId; }
    public void setQuestionId(Integer questionId) { this.questionId = questionId; }
    public String getPrefix() { return prefix; }
    public void setPrefix(String prefix) { this.prefix = prefix; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public Integer getItemOrder() { return itemOrder; }
    public void setItemOrder(Integer itemOrder) { this.itemOrder = itemOrder; }
}
