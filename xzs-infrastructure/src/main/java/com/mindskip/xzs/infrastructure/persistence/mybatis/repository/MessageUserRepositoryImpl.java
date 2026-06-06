package com.mindskip.xzs.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mindskip.xzs.domain.aggregate.message.MessageUser;
import com.mindskip.xzs.domain.gateway.MessageUserGateway;
import com.mindskip.xzs.infrastructure.persistence.mybatis.entity.MessageUserPo;
import com.mindskip.xzs.infrastructure.persistence.mybatis.mapper.MessageUserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MessageUserRepositoryImpl implements MessageUserGateway {
    private final MessageUserMapper mapper;

    public MessageUserRepositoryImpl(MessageUserMapper mapper) { this.mapper = mapper; }

    @Override
    public List<MessageUser> findByMessageId(Integer messageId) {
        var wrapper = new LambdaQueryWrapper<MessageUserPo>()
                .eq(MessageUserPo::getMessageId, messageId);
        return mapper.selectList(wrapper).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<MessageUser> findByUserId(Integer userId) {
        var wrapper = new LambdaQueryWrapper<MessageUserPo>()
                .eq(MessageUserPo::getReceiveUserId, userId)
                .orderByDesc(MessageUserPo::getId);
        return mapper.selectList(wrapper).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void save(MessageUser messageUser) {
        mapper.insert(toPo(messageUser));
    }

    @Override
    public void update(MessageUser messageUser) {
        mapper.updateById(toPo(messageUser));
    }

    private MessageUser toDomain(MessageUserPo po) {
        if (po == null) return null;
        MessageUser d = new MessageUser();
        d.setId(po.getId()); d.setMessageId(po.getMessageId());
        d.setReceiveUserId(po.getReceiveUserId()); d.setTitle(po.getTitle());
        d.setContent(po.getContent()); d.setReaded(po.getReaded());
        d.setCreateTime(po.getCreateTime()); d.setReadTime(po.getReadTime());
        return d;
    }

    private MessageUserPo toPo(MessageUser d) {
        if (d == null) return null;
        MessageUserPo po = new MessageUserPo();
        po.setId(d.getId()); po.setMessageId(d.getMessageId());
        po.setReceiveUserId(d.getReceiveUserId()); po.setTitle(d.getTitle());
        po.setContent(d.getContent()); po.setReaded(d.getReaded());
        po.setCreateTime(d.getCreateTime()); po.setReadTime(d.getReadTime());
        return po;
    }
}
