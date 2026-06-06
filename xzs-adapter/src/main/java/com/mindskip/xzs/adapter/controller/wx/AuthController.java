package com.mindskip.xzs.adapter.controller.wx;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.application.service.UserAppService;
import com.mindskip.xzs.exception.Result;
import com.mindskip.xzs.domain.aggregate.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/wx/student/auth")
public class AuthController extends BaseController {
    private final UserAppService userAppService;

    public AuthController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody Map<String, String> params) {
        String code = params.get("code");
        User user = userAppService.findByUserName("wx_" + code);
        if (user == null) {
            return Result.fail(1, "未绑定账号");
        }
        Map<String, Object> data = new HashMap<>();
        data.put("token", user.getToken());
        data.put("userId", user.getId());
        return Result.ok(data);
    }

    @PostMapping("/bind")
    public Result<Void> bind(@RequestBody Map<String, String> params) {
        Integer userId = getCurrentUserId();
        String wxOpenId = params.get("wxOpenId");
        User user = userAppService.findById(userId);
        if (user != null) {
            user.bindWx(wxOpenId);
            userAppService.update(user);
        }
        return Result.ok();
    }
}
