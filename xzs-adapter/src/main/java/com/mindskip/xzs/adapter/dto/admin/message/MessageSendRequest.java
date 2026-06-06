package com.mindskip.xzs.adapter.dto.admin.message;

import java.util.List;

public class MessageSendRequest {
    private String title;
    private String content;
    private List<Integer> receiveUserIds;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public List<Integer> getReceiveUserIds() { return receiveUserIds; }
    public void setReceiveUserIds(List<Integer> receiveUserIds) { this.receiveUserIds = receiveUserIds; }
}
