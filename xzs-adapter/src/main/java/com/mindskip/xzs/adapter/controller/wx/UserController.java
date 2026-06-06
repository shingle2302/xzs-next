package com.mindskip.xzs.adapter.controller.wx;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.adapter.dto.admin.user.UserResponse;
import com.mindskip.xzs.adapter.assembler.UserAssembler;
import com.mindskip.xzs.application.service.UserAppService;
import com.mindskip.xzs.exception.Result;
import com.mindskip.xzs.domain.aggregate.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/wx/student/user")
public class UserController extends BaseController {
    private final UserAppService userAppService;

    public UserController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @GetMapping("/current")
    public Result<UserResponse> current() {
        Integer userId = getCurrentUserId();
        User user = userAppService.findById(userId);
        return Result.ok(UserAssembler.INSTANCE.toResponse(user));
    }

    @PostMapping("/update")
    public Result<Void> update(@RequestBody Map<String, String> params) {
        Integer userId = getCurrentUserId();
        userAppService.updateUser(userId, params.get("realName"), params.get("phone"),
                params.get("email"), null, null, null);
        return Result.ok();
    }
}
