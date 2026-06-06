package com.mindskip.xzs.adapter.controller.student;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.adapter.dto.student.user.UserUpdateRequest;
import com.mindskip.xzs.adapter.dto.student.user.UserRegisterRequest;
import com.mindskip.xzs.adapter.dto.admin.user.UserResponse;
import com.mindskip.xzs.adapter.assembler.UserAssembler;
import com.mindskip.xzs.application.service.UserAppService;
import com.mindskip.xzs.exception.Result;
import com.mindskip.xzs.domain.aggregate.user.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/user")
public class UserController extends BaseController {
    private final UserAppService userAppService;

    public UserController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @PostMapping("/register")
    public Result<Void> register(@Valid @RequestBody UserRegisterRequest request) {
        userAppService.register(request.getUserName(), request.getPassword(), request.getRealName());
        return Result.ok();
    }

    @PostMapping("/update")
    public Result<Void> update(@Valid @RequestBody UserUpdateRequest request) {
        Integer userId = getCurrentUserId();
        userAppService.updateUser(userId, request.getRealName(), request.getPhone(),
                request.getEmail(), request.getAge(), request.getSex(), request.getBirthDay());
        return Result.ok();
    }

    @GetMapping("/current")
    public Result<UserResponse> current() {
        Integer userId = getCurrentUserId();
        User user = userAppService.findById(userId);
        return Result.ok(UserAssembler.INSTANCE.toResponse(user));
    }

    @PostMapping("/log")
    public Result<Void> log(@RequestBody com.mindskip.xzs.adapter.dto.student.user.UserEventLogRequest request) {
        Integer userId = getCurrentUserId();
        userAppService.logEvent(userId, request.getContent());
        return Result.ok();
    }
}
