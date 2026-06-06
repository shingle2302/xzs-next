package com.mindskip.xzs.adapter.dto.student.exam;

import java.util.List;

public class ExamPaperReadResponse {
    private Integer id;
    private String name;
    private Integer paperType;
    private Integer subjectId;
    private String subjectName;
    private Integer gradeLevel;
    private Integer score;
    private Integer questionCount;
    private Integer suggestTime;
    private Integer taskExamId;
    private List<ExamPaperTitleItemResponse> titleItems;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getPaperType() { return paperType; }
    public void setPaperType(Integer paperType) { this.paperType = paperType; }
    public Integer getSubjectId() { return subjectId; }
    public void setSubjectId(Integer subjectId) { this.subjectId = subjectId; }
    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }
    public Integer getGradeLevel() { return gradeLevel; }
    public void setGradeLevel(Integer gradeLevel) { this.gradeLevel = gradeLevel; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public Integer getQuestionCount() { return questionCount; }
    public void setQuestionCount(Integer questionCount) { this.questionCount = questionCount; }
    public Integer getSuggestTime() { return suggestTime; }
    public void setSuggestTime(Integer suggestTime) { this.suggestTime = suggestTime; }
    public Integer getTaskExamId() { return taskExamId; }
    public void setTaskExamId(Integer taskExamId) { this.taskExamId = taskExamId; }
    public List<ExamPaperTitleItemResponse> getTitleItems() { return titleItems; }
    public void setTitleItems(List<ExamPaperTitleItemResponse> titleItems) { this.titleItems = titleItems; }

    public static class ExamPaperTitleItemResponse {
        private String name;
        private List<ExamPaperQuestionItemResponse> questionItems;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public List<ExamPaperQuestionItemResponse> getQuestionItems() { return questionItems; }
        public void setQuestionItems(List<ExamPaperQuestionItemResponse> questionItems) { this.questionItems = questionItems; }
    }

    public static class ExamPaperQuestionItemResponse {
        private Integer id;
        private String name;
        private Integer score;
        private Integer questionType;
        private Integer itemOrder;

        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public Integer getScore() { return score; }
        public void setScore(Integer score) { this.score = score; }
        public Integer getQuestionType() { return questionType; }
        public void setQuestionType(Integer questionType) { this.questionType = questionType; }
        public Integer getItemOrder() { return itemOrder; }
        public void setItemOrder(Integer itemOrder) { this.itemOrder = itemOrder; }
    }
}
