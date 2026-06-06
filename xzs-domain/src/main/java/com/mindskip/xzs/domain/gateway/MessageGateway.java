package com.mindskip.xzs.domain.gateway;

import java.util.List;
import com.mindskip.xzs.domain.aggregate.message.MessageUser;
import com.mindskip.xzs.domain.aggregate.message.Message;

public interface MessageGateway {
    Message findById(Integer id);
    List<Message> findPage(Integer pageIndex, Integer pageSize, Integer receiveUserId);
    long count(Integer receiveUserId);
    long unreadCount(Integer receiveUserId);
    void save(Message message);
    void update(Message message);
    void delete(Integer id);
}
