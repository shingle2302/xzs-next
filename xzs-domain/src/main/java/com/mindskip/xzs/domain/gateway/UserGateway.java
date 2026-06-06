package com.mindskip.xzs.domain.gateway;

import com.mindskip.xzs.domain.aggregate.user.User;

import java.util.List;

public interface UserGateway {
    User findById(Integer id);
    User findByUserName(String userName);
    User findByWxOpenId(String wxOpenId);
    List<User> findByIds(List<Integer> ids);
    List<User> findPage(Integer pageIndex, Integer pageSize, String userName, Integer role);
    long count(String userName, Integer role);
    long countAll();
    void save(User user);
    void update(User user);
    void softDelete(Integer id);
}
