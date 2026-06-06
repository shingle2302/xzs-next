package com.mindskip.xzs.application.listener;

import com.mindskip.xzs.domain.event.UserRegisteredEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserLogListener {
    private static final Logger log = LoggerFactory.getLogger(UserLogListener.class);

    @EventListener
    public void handleUserRegistered(UserRegisteredEvent event) {
        log.info("用户注册事件: userId={}, userName={}", event.getUserId(), event.getUserName());
    }
}
