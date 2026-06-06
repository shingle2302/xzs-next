package com.mindskip.xzs.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mindskip.xzs.domain.aggregate.user.User;
import com.mindskip.xzs.domain.gateway.UserGateway;
import com.mindskip.xzs.infrastructure.persistence.mybatis.entity.UserPo;
import com.mindskip.xzs.infrastructure.persistence.mybatis.mapper.UserMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserGateway {
    private final UserMapper userMapper;

    public UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findById(Integer id) {
        UserPo po = userMapper.selectById(id);
        return toDomain(po);
    }

    @Override
    public User findByUserName(String userName) {
        UserPo po = userMapper.findByUserName(userName);
        return toDomain(po);
    }

    @Override
    public User findByWxOpenId(String wxOpenId) {
        UserPo po = userMapper.findByWxOpenId(wxOpenId);
        return toDomain(po);
    }

    @Override
    public List<User> findByIds(List<Integer> ids) {
        List<UserPo> pos = userMapper.selectBatchIds(ids);
        return pos.stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<User> findPage(Integer pageIndex, Integer pageSize, String userName, Integer role) {
        Page<UserPo> page = new Page<>(pageIndex, pageSize);
        IPage<UserPo> result = userMapper.findPage(page, userName, role);
        return result.getRecords().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public long count(String userName, Integer role) {
        return userMapper.count(userName, role);
    }

    @Override
    public long countAll() {
        return userMapper.countAll();
    }

    @Override
    public void save(User user) {
        userMapper.insert(toPo(user));
        user.setId(user.getId());
    }

    @Override
    public void update(User user) {
        userMapper.updateById(toPo(user));
    }

    @Override
    public void softDelete(Integer id) {
        userMapper.deleteById(id);
    }

    private User toDomain(UserPo po) {
        if (po == null) return null;
        User user = new User();
        user.setId(po.getId());
        user.setUserUuid(po.getUserUuid());
        user.setUserName(po.getUserName());
        user.setPassword(po.getPassword());
        user.setRealName(po.getRealName());
        user.setAge(po.getAge());
        user.setSex(po.getSex());
        user.setBirthDay(po.getBirthDay());
        user.setUserLevel(po.getUserLevel());
        user.setPhone(po.getPhone());
        user.setEmail(po.getEmail());
        user.setRole(po.getRole());
        user.setStatus(po.getStatus());
        user.setImagePath(po.getImagePath());
        user.setCreateTime(po.getCreateTime());
        user.setModifyTime(po.getModifyTime());
        user.setLastActiveTime(po.getLastActiveTime());
        user.setDeleted(po.getDeleted());
        user.setWxOpenId(po.getWxOpenId());
        return user;
    }

    private UserPo toPo(User user) {
        if (user == null) return null;
        UserPo po = new UserPo();
        po.setId(user.getId());
        po.setUserUuid(user.getUserUuid());
        po.setUserName(user.getUserName());
        po.setPassword(user.getPassword());
        po.setRealName(user.getRealName());
        po.setAge(user.getAge());
        po.setSex(user.getSex());
        po.setBirthDay(user.getBirthDay());
        po.setUserLevel(user.getUserLevel());
        po.setPhone(user.getPhone());
        po.setEmail(user.getEmail());
        po.setRole(user.getRole());
        po.setStatus(user.getStatus());
        po.setImagePath(user.getImagePath());
        po.setCreateTime(user.getCreateTime());
        po.setModifyTime(user.getModifyTime());
        po.setLastActiveTime(user.getLastActiveTime());
        po.setDeleted(user.getDeleted());
        po.setWxOpenId(user.getWxOpenId());
        return po;
    }
}
