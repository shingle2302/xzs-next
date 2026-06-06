package com.mindskip.xzs.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mindskip.xzs.domain.aggregate.message.Message;
import com.mindskip.xzs.domain.gateway.MessageGateway;
import com.mindskip.xzs.infrastructure.persistence.mybatis.entity.MessagePo;
import com.mindskip.xzs.infrastructure.persistence.mybatis.mapper.MessageMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MessageRepositoryImpl implements MessageGateway {
    private final MessageMapper mapper;

    public MessageRepositoryImpl(MessageMapper mapper) { this.mapper = mapper; }

    @Override
    public Message findById(Integer id) {
        return toDomain(mapper.selectById(id));
    }

    @Override
    public List<Message> findPage(Integer pageIndex, Integer pageSize, Integer receiveUserId) {
        var wrapper = new LambdaQueryWrapper<MessagePo>()
                .eq(MessagePo::getReceiveUserId, receiveUserId)
                .orderByDesc(MessagePo::getId);
        IPage<MessagePo> page = mapper.selectPage(new Page<>(pageIndex, pageSize), wrapper);
        return page.getRecords().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public long count(Integer receiveUserId) {
        return mapper.selectCount(
                new LambdaQueryWrapper<MessagePo>().eq(MessagePo::getReceiveUserId, receiveUserId));
    }

    @Override
    public long unreadCount(Integer receiveUserId) {
        return mapper.selectCount(
                new LambdaQueryWrapper<MessagePo>()
                        .eq(MessagePo::getReceiveUserId, receiveUserId)
                        .eq(MessagePo::getReaded, false));
    }

    @Override
    public void delete(Integer id) {
        mapper.deleteById(id);
    }

    @Override
    public void save(Message message) {
        mapper.insert(toPo(message));
    }

    @Override
    public void update(Message message) {
        mapper.updateById(toPo(message));
    }

    private Message toDomain(MessagePo po) {
        if (po == null) return null;
        Message d = new Message();
        d.setId(po.getId()); d.setTitle(po.getTitle()); d.setContent(po.getContent());
        d.setSendUserId(po.getSendUserId()); d.setSendUserName(po.getSendUserName());
        d.setSendRealName(po.getSendRealName()); d.setReceiveUserId(po.getReceiveUserId());
        d.setReceiveUserName(po.getReceiveUserName()); d.setReceiveRealName(po.getReceiveRealName());
        d.setReaded(po.getReaded()); d.setCreateTime(po.getCreateTime());
        return d;
    }

    private MessagePo toPo(Message d) {
        if (d == null) return null;
        MessagePo po = new MessagePo();
        po.setId(d.getId()); po.setTitle(d.getTitle()); po.setContent(d.getContent());
        po.setSendUserId(d.getSendUserId()); po.setSendUserName(d.getSendUserName());
        po.setSendRealName(d.getSendRealName()); po.setReceiveUserId(d.getReceiveUserId());
        po.setReceiveUserName(d.getReceiveUserName()); po.setReceiveRealName(d.getReceiveRealName());
        po.setReaded(d.getReaded()); po.setCreateTime(d.getCreateTime());
        return po;
    }
}
