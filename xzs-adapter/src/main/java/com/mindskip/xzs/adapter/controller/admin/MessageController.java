package com.mindskip.xzs.adapter.controller.admin;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.adapter.dto.admin.message.MessagePageRequest;
import com.mindskip.xzs.adapter.dto.admin.message.MessageSendRequest;
import com.mindskip.xzs.adapter.dto.common.PageResponse;
import com.mindskip.xzs.exception.Result;
import com.mindskip.xzs.application.service.MessageAppService;
import com.mindskip.xzs.application.service.UserAppService;
import com.mindskip.xzs.domain.aggregate.message.Message;
import com.mindskip.xzs.domain.aggregate.user.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/message")
public class MessageController extends BaseController {
    private final MessageAppService messageAppService;
    private final UserAppService userAppService;

    public MessageController(MessageAppService messageAppService, UserAppService userAppService) {
        this.messageAppService = messageAppService;
        this.userAppService = userAppService;
    }

    @PostMapping("/page")
    public Result<PageResponse<Message>> page(@Valid @RequestBody MessagePageRequest request) {
        requireRole(1, 3);
        List<Message> list = messageAppService.findPage(request.getPageIndex(), request.getPageSize(), null);
        long total = messageAppService.count(null);
        return Result.ok(PageResponse.of(list, total, request.getPageIndex(), request.getPageSize()));
    }

    @PostMapping("/send")
    public Result<Void> send(@Valid @RequestBody MessageSendRequest request) {
        requireRole(1, 3);
        Integer userId = getCurrentUserId();
        User sendUser = userAppService.findById(userId);

        Message message = new Message();
        message.setTitle(request.getTitle());
        message.setContent(request.getContent());
        message.setSendUserId(userId);
        message.setSendUserName(sendUser.getUserName());
        message.setSendRealName(sendUser.getRealName());
        message.setCreateTime(LocalDateTime.now());

        List<User> receiveUsers = userAppService.findByIds(request.getReceiveUserIds());
        messageAppService.sendMessage(message, request.getReceiveUserIds(), receiveUsers);
        return Result.ok();
    }

    @PostMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        requireRole(1, 3);
        messageAppService.delete(id);
        return Result.ok();
    }
}
