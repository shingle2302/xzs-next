package com.mindskip.xzs.infrastructure.persistence.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

@TableName("t_task_exam_customer_answer")
public class TaskExamCustomerAnswerPo {
    private Integer id;
    private Integer taskExamId;
    private Integer createUser;
    private LocalDateTime createTime;
    private Integer textContentId;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getTaskExamId() { return taskExamId; }
    public void setTaskExamId(Integer taskExamId) { this.taskExamId = taskExamId; }
    public Integer getCreateUser() { return createUser; }
    public void setCreateUser(Integer createUser) { this.createUser = createUser; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public Integer getTextContentId() { return textContentId; }
    public void setTextContentId(Integer textContentId) { this.textContentId = textContentId; }
}
