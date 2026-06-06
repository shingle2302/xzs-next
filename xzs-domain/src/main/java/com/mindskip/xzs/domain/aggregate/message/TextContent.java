package com.mindskip.xzs.domain.aggregate.message;

import java.time.LocalDateTime;

public class TextContent {
    private Integer id;
    private String content;
    private LocalDateTime createTime;

    public TextContent() {}

    public TextContent(String content) {
        this.content = content;
        this.createTime = LocalDateTime.now();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
}
