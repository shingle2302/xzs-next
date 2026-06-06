package com.mindskip.xzs.adapter.dto.admin.exam;

import java.util.List;

public class ExamPaperEditRequest {
    private Integer id;
    private Integer subjectId;
    private Integer paperType;
    private Integer gradeLevel;
    private String name;
    private Integer suggestTime;
    private List<ExamPaperTitleItem> titleItems;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getSubjectId() { return subjectId; }
    public void setSubjectId(Integer subjectId) { this.subjectId = subjectId; }
    public Integer getPaperType() { return paperType; }
    public void setPaperType(Integer paperType) { this.paperType = paperType; }
    public Integer getGradeLevel() { return gradeLevel; }
    public void setGradeLevel(Integer gradeLevel) { this.gradeLevel = gradeLevel; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getSuggestTime() { return suggestTime; }
    public void setSuggestTime(Integer suggestTime) { this.suggestTime = suggestTime; }
    public List<ExamPaperTitleItem> getTitleItems() { return titleItems; }
    public void setTitleItems(List<ExamPaperTitleItem> titleItems) { this.titleItems = titleItems; }

    public static class ExamPaperTitleItem {
        private String name;
        private List<ExamPaperQuestionItem> questionItems;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public List<ExamPaperQuestionItem> getQuestionItems() { return questionItems; }
        public void setQuestionItems(List<ExamPaperQuestionItem> questionItems) { this.questionItems = questionItems; }
    }

    public static class ExamPaperQuestionItem {
        private Integer id;
        private Integer itemOrder;

        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
        public Integer getItemOrder() { return itemOrder; }
        public void setItemOrder(Integer itemOrder) { this.itemOrder = itemOrder; }
    }
}
