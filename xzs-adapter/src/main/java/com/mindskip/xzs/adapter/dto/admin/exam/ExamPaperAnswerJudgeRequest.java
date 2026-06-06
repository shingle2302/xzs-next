package com.mindskip.xzs.adapter.dto.admin.exam;

import java.util.List;

public class ExamPaperAnswerJudgeRequest {
    private Integer id;
    private List<JudgeItem> answerItems;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public List<JudgeItem> getAnswerItems() { return answerItems; }
    public void setAnswerItems(List<JudgeItem> answerItems) { this.answerItems = answerItems; }

    public static class JudgeItem {
        private Integer id;
        private String score;

        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
        public String getScore() { return score; }
        public void setScore(String score) { this.score = score; }
    }
}
