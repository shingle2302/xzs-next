package com.mindskip.xzs.infrastructure.persistence.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

@TableName("subject")
public class SubjectPo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer level;
    private Integer level1;
    private Integer level2;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableLogic
    private Boolean deleted;

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
