package com.mindskip.xzs.domain.gateway;

public interface UserEventLogGateway {
    void save(Integer userId, String userName, String realName, String content);
}
