package com.mindskip.xzs.application.service;

import com.mindskip.xzs.domain.aggregate.message.Message;
import com.mindskip.xzs.domain.aggregate.message.MessageUser;
import com.mindskip.xzs.domain.gateway.MessageGateway;
import com.mindskip.xzs.domain.gateway.MessageUserGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageAppService {
    private final MessageGateway messageGateway;
    private final MessageUserGateway messageUserGateway;

    public MessageAppService(MessageGateway messageGateway, MessageUserGateway messageUserGateway) {
        this.messageGateway = messageGateway;
        this.messageUserGateway = messageUserGateway;
    }

    public Message findById(Integer id) {
        return messageGateway.findById(id);
    }

    public List<Message> findPage(Integer pageIndex, Integer pageSize, Integer receiveUserId) {
        return messageGateway.findPage(pageIndex, pageSize, receiveUserId);
    }

    public long count(Integer receiveUserId) {
        return messageGateway.count(receiveUserId);
    }

    public long unreadCount(Integer userId) {
        return messageGateway.unreadCount(userId);
    }

    public List<MessageUser> findUserPage(Integer pageIndex, Integer pageSize, Integer userId) {
        return messageUserGateway.findByUserId(userId);
    }

    public long countUser(Integer userId) {
        return messageUserGateway.findByUserId(userId).size();
    }

    @Transactional
    public void sendMessage(com.mindskip.xzs.domain.aggregate.message.Message message,
                            List<Integer> receiveUserIds,
                            List<com.mindskip.xzs.domain.aggregate.user.User> receiveUsers) {
        messageGateway.save(message);
        for (var user : receiveUsers) {
            MessageUser mu = new MessageUser();
            mu.setMessageId(message.getId());
            mu.setReceiveUserId(user.getId());
            mu.setTitle(message.getTitle());
            mu.setContent(message.getContent());
            mu.setReaded(false);
            mu.setCreateTime(LocalDateTime.now());
            messageUserGateway.save(mu);
        }
    }

    @Transactional
    public void delete(Integer id) {
        messageGateway.delete(id);
    }

    @Transactional
    public void markRead(Integer id, Integer userId) {
        List<MessageUser> list = messageUserGateway.findByUserId(userId);
        for (var mu : list) {
            if (mu.getId().equals(id) || mu.getMessageId().equals(id)) {
                mu.setReaded(true);
                mu.setReadTime(LocalDateTime.now());
                messageUserGateway.update(mu);
            }
        }
    }

    @Transactional
    public void deleteUserMessage(Integer id, Integer userId) {
        List<MessageUser> list = messageUserGateway.findByUserId(userId);
        for (var mu : list) {
            if (mu.getId().equals(id)) {
                mu.setReaded(true);
                messageUserGateway.update(mu);
            }
        }
    }

    @Transactional
    public void saveMessage(Message message) {
        messageGateway.save(message);
    }
}
