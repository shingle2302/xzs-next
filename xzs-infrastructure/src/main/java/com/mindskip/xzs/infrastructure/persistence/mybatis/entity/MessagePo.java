package com.mindskip.xzs.infrastructure.persistence.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

@TableName("t_message")
public class MessagePo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private Integer sendUserId;
    private String sendUserName;
    private String sendRealName;
    private Integer receiveUserId;
    private String receiveUserName;
    private String receiveRealName;
    private Boolean readed;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Integer getSendUserId() { return sendUserId; }
    public void setSendUserId(Integer sendUserId) { this.sendUserId = sendUserId; }
    public String getSendUserName() { return sendUserName; }
    public void setSendUserName(String sendUserName) { this.sendUserName = sendUserName; }
    public String getSendRealName() { return sendRealName; }
    public void setSendRealName(String sendRealName) { this.sendRealName = sendRealName; }
    public Integer getReceiveUserId() { return receiveUserId; }
    public void setReceiveUserId(Integer receiveUserId) { this.receiveUserId = receiveUserId; }
    public String getReceiveUserName() { return receiveUserName; }
    public void setReceiveUserName(String receiveUserName) { this.receiveUserName = receiveUserName; }
    public String getReceiveRealName() { return receiveRealName; }
    public void setReceiveRealName(String receiveRealName) { this.receiveRealName = receiveRealName; }
    public Boolean getReaded() { return readed; }
    public void setReaded(Boolean readed) { this.readed = readed; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
}
