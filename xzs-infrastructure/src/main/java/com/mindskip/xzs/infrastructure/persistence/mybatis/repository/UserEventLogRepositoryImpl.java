package com.mindskip.xzs.infrastructure.persistence.mybatis.repository;

import com.mindskip.xzs.domain.gateway.UserEventLogGateway;
import com.mindskip.xzs.infrastructure.persistence.mybatis.entity.UserEventLogPo;
import com.mindskip.xzs.infrastructure.persistence.mybatis.mapper.UserEventLogMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class UserEventLogRepositoryImpl implements UserEventLogGateway {
    private final UserEventLogMapper mapper;

    public UserEventLogRepositoryImpl(UserEventLogMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void save(Integer userId, String userName, String realName, String content) {
        UserEventLogPo po = new UserEventLogPo();
        po.setUserId(userId);
        po.setUserName(userName);
        po.setRealName(realName);
        po.setContent(content);
        po.setCreateTime(LocalDateTime.now());
        mapper.insert(po);
    }
}
