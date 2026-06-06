package com.mindskip.xzs.adapter.dto.admin.task;

public class TaskExamRequest {
    private Integer id;
    private String title;
    private Integer gradeLevel;
    private java.util.List<Integer> paperIds;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Integer getGradeLevel() { return gradeLevel; }
    public void setGradeLevel(Integer gradeLevel) { this.gradeLevel = gradeLevel; }
    public java.util.List<Integer> getPaperIds() { return paperIds; }
    public void setPaperIds(java.util.List<Integer> paperIds) { this.paperIds = paperIds; }
}
