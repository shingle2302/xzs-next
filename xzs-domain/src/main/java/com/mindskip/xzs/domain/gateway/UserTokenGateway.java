package com.mindskip.xzs.domain.gateway;

import com.mindskip.xzs.domain.aggregate.user.UserToken;

public interface UserTokenGateway {
    UserToken findByUserId(Integer userId);
    UserToken findByToken(String token);
    void save(UserToken userToken);
    void update(UserToken userToken);
    void deleteByUserId(Integer userId);
}
