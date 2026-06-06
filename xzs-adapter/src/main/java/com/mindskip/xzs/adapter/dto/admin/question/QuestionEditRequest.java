package com.mindskip.xzs.adapter.dto.admin.question;

import java.util.List;

public class QuestionEditRequest {
    private Integer id;
    private Integer questionType;
    private Integer subjectId;
    private Integer gradeLevel;
    private Integer difficult;
    private String correctAnswer;
    private String title;
    private String analyze;
    private List<QuestionItem> items;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getQuestionType() { return questionType; }
    public void setQuestionType(Integer questionType) { this.questionType = questionType; }
    public Integer getSubjectId() { return subjectId; }
    public void setSubjectId(Integer subjectId) { this.subjectId = subjectId; }
    public Integer getGradeLevel() { return gradeLevel; }
    public void setGradeLevel(Integer gradeLevel) { this.gradeLevel = gradeLevel; }
    public Integer getDifficult() { return difficult; }
    public void setDifficult(Integer difficult) { this.difficult = difficult; }
    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAnalyze() { return analyze; }
    public void setAnalyze(String analyze) { this.analyze = analyze; }
    public List<QuestionItem> getItems() { return items; }
    public void setItems(List<QuestionItem> items) { this.items = items; }

    public static class QuestionItem {
        private String prefix;
        private String content;

        public String getPrefix() { return prefix; }
        public void setPrefix(String prefix) { this.prefix = prefix; }
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
    }
}
