package com.mindskip.xzs.domain.aggregate.subject;

import java.time.LocalDateTime;

public class Subject {
    private Integer id;
    private String name;
    private Integer level;
    private Integer level1;
    private Integer level2;
    private LocalDateTime createTime;
    private Boolean deleted;

    public Subject() {}

    public Subject(String name, Integer level) {
        this.name = name;
        this.level = level;
        this.createTime = LocalDateTime.now();
        this.deleted = false;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }
    public Integer getLevel1() { return level1; }
    public void setLevel1(Integer level1) { this.level1 = level1; }
    public Integer getLevel2() { return level2; }
    public void setLevel2(Integer level2) { this.level2 = level2; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }
}
