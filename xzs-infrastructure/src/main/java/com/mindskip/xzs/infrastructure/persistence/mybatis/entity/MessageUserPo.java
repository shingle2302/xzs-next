package com.mindskip.xzs.infrastructure.persistence.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

@TableName("t_message_user")
public class MessageUserPo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer messageId;
    private Integer receiveUserId;
    private String title;
    private String content;
    private Boolean readed;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    private LocalDateTime readTime;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getMessageId() { return messageId; }
    public void setMessageId(Integer messageId) { this.messageId = messageId; }
    public Integer getReceiveUserId() { return receiveUserId; }
    public void setReceiveUserId(Integer receiveUserId) { this.receiveUserId = receiveUserId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Boolean getReaded() { return readed; }
    public void setReaded(Boolean readed) { this.readed = readed; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public LocalDateTime getReadTime() { return readTime; }
    public void setReadTime(LocalDateTime readTime) { this.readTime = readTime; }
}
