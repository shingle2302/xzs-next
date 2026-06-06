package com.mindskip.xzs.application.service;

import com.mindskip.xzs.domain.aggregate.user.User;
import com.mindskip.xzs.domain.aggregate.user.UserToken;
import com.mindskip.xzs.domain.event.UserRegisteredEvent;
import com.mindskip.xzs.domain.gateway.UserEventLogGateway;
import com.mindskip.xzs.domain.gateway.UserGateway;
import com.mindskip.xzs.domain.gateway.UserTokenGateway;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserAppService {
    private final UserGateway userGateway;
    private final UserTokenGateway userTokenGateway;
    private final UserEventLogGateway userEventLogGateway;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    public UserAppService(UserGateway userGateway, UserTokenGateway userTokenGateway,
                          UserEventLogGateway userEventLogGateway,
                          PasswordEncoder passwordEncoder, ApplicationEventPublisher eventPublisher) {
        this.userGateway = userGateway;
        this.userTokenGateway = userTokenGateway;
        this.userEventLogGateway = userEventLogGateway;
        this.passwordEncoder = passwordEncoder;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public User register(String userName, String password, Integer role) {
        if (userGateway.findByUserName(userName) != null) {
            throw new IllegalArgumentException("用户名已存在");
        }
        User user = new User(userName, passwordEncoder.encode(password), role);
        user.setUserUuid(UUID.randomUUID().toString().replace("-", ""));
        userGateway.save(user);
        eventPublisher.publishEvent(new UserRegisteredEvent(user.getId(), user.getUserName()));
        return user;
    }

    @Transactional
    public User register(String userName, String password, String realName) {
        if (userGateway.findByUserName(userName) != null) {
            throw new IllegalArgumentException("用户名已存在");
        }
        User user = new User(userName, passwordEncoder.encode(password), 2);
        user.setRealName(realName);
        user.setUserUuid(UUID.randomUUID().toString().replace("-", ""));
        userGateway.save(user);
        eventPublisher.publishEvent(new UserRegisteredEvent(user.getId(), user.getUserName()));
        return user;
    }

    @Transactional
    public User login(String userName, String password) {
        User user = userGateway.findByUserName(userName);
        if (user == null) throw new IllegalArgumentException("用户名或密码错误");
        if (user.isDisabled()) throw new IllegalArgumentException("帐户已被禁用");
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("用户名或密码错误");
        }
        String token = UUID.randomUUID().toString().replace("-", "");
        user.setToken(token);
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken.setUserId(user.getId());
        userToken.setUserName(user.getUserName());
        userToken.setCreateTime(LocalDateTime.now());
        userToken.setExpireTime(LocalDateTime.now().plusDays(7));
        userTokenGateway.deleteByUserId(user.getId());
        userTokenGateway.save(userToken);
        return user;
    }

    @Transactional
    public void logout(Integer userId) {
        userTokenGateway.deleteByUserId(userId);
    }

    public User findByUserName(String userName) {
        return userGateway.findByUserName(userName);
    }

    public List<User> findByIds(List<Integer> ids) {
        return userGateway.findByIds(ids);
    }

    public User findById(Integer id) {
        return userGateway.findById(id);
    }

    public List<User> findPage(Integer pageIndex, Integer pageSize, String userName, Integer role) {
        return userGateway.findPage(pageIndex, pageSize, userName, role);
    }

    public long count(String userName, Integer role) {
        return userGateway.count(userName, role);
    }

    public long countAll() {
        return userGateway.countAll();
    }

    @Transactional
    public void update(User user) {
        userGateway.update(user);
    }

    @Transactional
    public void changePassword(Integer userId, String oldPassword, String newPassword) {
        User user = userGateway.findById(userId);
        if (user == null) throw new IllegalArgumentException("用户不存在");
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("原密码错误");
        }
        user.changePassword(passwordEncoder.encode(newPassword));
        userGateway.update(user);
    }

    @Transactional
    public void disableUser(Integer userId) {
        User user = userGateway.findById(userId);
        if (user != null) {
            user.disable();
            userGateway.update(user);
        }
    }

    @Transactional
    public void changeStatus(Integer id) {
        User user = userGateway.findById(id);
        if (user != null) {
            if (user.isDisabled()) {
                user.enable();
            } else {
                user.disable();
            }
            userGateway.update(user);
        }
    }

    @Transactional
    public void createUser(User user) {
        if (userGateway.findByUserName(user.getUserName()) != null) {
            throw new IllegalArgumentException("用户名已存在");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserUuid(UUID.randomUUID().toString().replace("-", ""));
        userGateway.save(user);
    }

    @Transactional
    public void updateUser(Integer userId, String realName, String phone, String email,
                           Integer age, Integer sex, String birthDay) {
        User user = userGateway.findById(userId);
        if (user == null) throw new IllegalArgumentException("用户不存在");
        user.setRealName(realName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setAge(age);
        user.setSex(sex);
        if (birthDay != null && !birthDay.isBlank()) {
            try {
                user.setBirthDay(java.time.LocalDateTime.parse(birthDay + "T00:00:00"));
            } catch (Exception ignored) {}
        }
        userGateway.update(user);
    }

    @Transactional
    public void logEvent(Integer userId, String content) {
        var user = userGateway.findById(userId);
        if (user == null) return;
        userEventLogGateway.save(userId, user.getUserName(), user.getRealName(), content);
    }

    @Transactional
    public void softDelete(Integer id) {
        userGateway.softDelete(id);
    }
}
