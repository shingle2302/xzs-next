package com.mindskip.xzs.domain.aggregate.message;

import java.time.LocalDateTime;

public class Message {
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
    private String receiveUserIds;
    private Integer receiveUserCount;
    private LocalDateTime createTime;
    private Boolean deleted;

    public Message() {}

    public Message(String title, String content, Integer sendUserId, String sendUserName) {
        this.title = title;
        this.content = content;
        this.sendUserId = sendUserId;
        this.sendUserName = sendUserName;
        this.createTime = LocalDateTime.now();
        this.deleted = false;
    }

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
    public String getReceiveUserIds() { return receiveUserIds; }
    public void setReceiveUserIds(String receiveUserIds) { this.receiveUserIds = receiveUserIds; }
    public Integer getReceiveUserCount() { return receiveUserCount; }
    public void setReceiveUserCount(Integer receiveUserCount) { this.receiveUserCount = receiveUserCount; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }
}
