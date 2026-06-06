package com.mindskip.xzs.domain.gateway;

import com.mindskip.xzs.domain.aggregate.message.MessageUser;
import java.util.List;

public interface MessageUserGateway {
    List<MessageUser> findByMessageId(Integer messageId);
    List<MessageUser> findByUserId(Integer userId);
    void save(MessageUser messageUser);
    void update(MessageUser messageUser);
}
