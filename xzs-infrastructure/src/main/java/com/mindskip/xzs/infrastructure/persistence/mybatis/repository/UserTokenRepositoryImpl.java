package com.mindskip.xzs.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mindskip.xzs.domain.aggregate.user.UserToken;
import com.mindskip.xzs.domain.gateway.UserTokenGateway;
import com.mindskip.xzs.infrastructure.persistence.mybatis.entity.UserTokenPo;
import com.mindskip.xzs.infrastructure.persistence.mybatis.mapper.UserTokenMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserTokenRepositoryImpl implements UserTokenGateway {
    private final UserTokenMapper mapper;

    public UserTokenRepositoryImpl(UserTokenMapper mapper) { this.mapper = mapper; }

    @Override
    public UserToken findByToken(String token) {
        return toDomain(mapper.selectOne(
                new LambdaQueryWrapper<UserTokenPo>().eq(UserTokenPo::getToken, token)));
    }

    @Override
    public UserToken findByUserId(Integer userId) {
        return toDomain(mapper.selectOne(
                new LambdaQueryWrapper<UserTokenPo>().eq(UserTokenPo::getUserId, userId)));
    }

    @Override
    public void save(UserToken userToken) {
        mapper.insert(toPo(userToken));
    }

    @Override
    public void update(UserToken userToken) {
        mapper.updateById(toPo(userToken));
    }

    @Override
    public void deleteByUserId(Integer userId) {
        mapper.delete(new LambdaQueryWrapper<UserTokenPo>().eq(UserTokenPo::getUserId, userId));
    }

    private UserToken toDomain(UserTokenPo po) {
        if (po == null) return null;
        UserToken d = new UserToken();
        d.setId(po.getId()); d.setToken(po.getToken()); d.setUserId(po.getUserId());
        d.setUserName(po.getUserName()); d.setCreateTime(po.getCreateTime());
        d.setExpireTime(po.getExpireTime());
        return d;
    }

    private UserTokenPo toPo(UserToken d) {
        if (d == null) return null;
        UserTokenPo po = new UserTokenPo();
        po.setId(d.getId()); po.setToken(d.getToken()); po.setUserId(d.getUserId());
        po.setUserName(d.getUserName()); po.setCreateTime(d.getCreateTime());
        po.setExpireTime(d.getExpireTime());
        return po;
    }
}
