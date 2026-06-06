package com.mindskip.xzs.adapter.controller.student;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.adapter.dto.common.PageRequest;
import com.mindskip.xzs.adapter.dto.common.PageResponse;
import com.mindskip.xzs.application.service.MessageAppService;
import com.mindskip.xzs.exception.Result;
import com.mindskip.xzs.domain.aggregate.message.MessageUser;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/message")
public class MessageController extends BaseController {
    private final MessageAppService messageAppService;

    public MessageController(MessageAppService messageAppService) {
        this.messageAppService = messageAppService;
    }

    @PostMapping("/page")
    public Result<PageResponse<MessageUser>> page(@Valid @RequestBody PageRequest request) {
        Integer userId = getCurrentUserId();
        List<MessageUser> list = messageAppService.findUserPage(request.getPageIndex(), request.getPageSize(), userId);
        long total = messageAppService.countUser(userId);
        return Result.ok(PageResponse.of(list, total, request.getPageIndex(), request.getPageSize()));
    }

    @GetMapping("/unread-count")
    public Result<Long> unreadCount() {
        Integer userId = getCurrentUserId();
        return Result.ok(messageAppService.unreadCount(userId));
    }

    @PostMapping("/read/{id}")
    public Result<Void> read(@PathVariable Integer id) {
        Integer userId = getCurrentUserId();
        messageAppService.markRead(id, userId);
        return Result.ok();
    }

    @PostMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        Integer userId = getCurrentUserId();
        messageAppService.deleteUserMessage(id, userId);
        return Result.ok();
    }
}
