package com.mindskip.xzs.infrastructure.persistence.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

@TableName("t_exam_paper_question_customer_answer")
public class ExamPaperQuestionCustomerAnswerPo {
    private Integer id;
    private Integer questionId;
    private Integer examPaperId;
    private Integer examPaperAnswerId;
    private Integer subjectId;
    private Integer userId;
    private Integer questionType;
    private Integer customerScore;
    private Integer questionScore;
    private String questionTextContentId;
    private String answer;
    private String textContentId;
    private Boolean doRight;
    private LocalDateTime createTime;
    private Integer itemOrder;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getQuestionId() { return questionId; }
    public void setQuestionId(Integer questionId) { this.questionId = questionId; }
    public Integer getExamPaperId() { return examPaperId; }
    public void setExamPaperId(Integer examPaperId) { this.examPaperId = examPaperId; }
    public Integer getExamPaperAnswerId() { return examPaperAnswerId; }
    public void setExamPaperAnswerId(Integer examPaperAnswerId) { this.examPaperAnswerId = examPaperAnswerId; }
    public Integer getSubjectId() { return subjectId; }
    public void setSubjectId(Integer subjectId) { this.subjectId = subjectId; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getQuestionType() { return questionType; }
    public void setQuestionType(Integer questionType) { this.questionType = questionType; }
    public Integer getCustomerScore() { return customerScore; }
    public void setCustomerScore(Integer customerScore) { this.customerScore = customerScore; }
    public Integer getQuestionScore() { return questionScore; }
    public void setQuestionScore(Integer questionScore) { this.questionScore = questionScore; }
    public String getQuestionTextContentId() { return questionTextContentId; }
    public void setQuestionTextContentId(String questionTextContentId) { this.questionTextContentId = questionTextContentId; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
    public String getTextContentId() { return textContentId; }
    public void setTextContentId(String textContentId) { this.textContentId = textContentId; }
    public Boolean getDoRight() { return doRight; }
    public void setDoRight(Boolean doRight) { this.doRight = doRight; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public Integer getItemOrder() { return itemOrder; }
    public void setItemOrder(Integer itemOrder) { this.itemOrder = itemOrder; }
}
