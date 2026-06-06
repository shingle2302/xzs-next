package com.mindskip.xzs.adapter.dto.admin.message;

import com.mindskip.xzs.adapter.dto.common.PageRequest;

public class MessagePageRequest extends PageRequest {
    private String sendUserName;

    public String getSendUserName() { return sendUserName; }
    public void setSendUserName(String sendUserName) { this.sendUserName = sendUserName; }
}
