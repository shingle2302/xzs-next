package com.mindskip.xzs.adapter.controller;

import com.mindskip.xzs.adapter.dto.auth.LoginRequest;
import com.mindskip.xzs.adapter.dto.auth.LoginResponse;
import com.mindskip.xzs.application.service.UserAppService;
import com.mindskip.xzs.exception.Result;
import com.mindskip.xzs.enums.RoleEnum;
import com.mindskip.xzs.domain.aggregate.user.User;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController extends BaseController {
    private final UserAppService userAppService;
    private final AuthenticationManager authenticationManager;

    public LoginController(UserAppService userAppService, AuthenticationManager authenticationManager) {
        this.userAppService = userAppService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public Result<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        User user = userAppService.login(request.getUserName(), request.getPassword());
        LoginResponse resp = new LoginResponse();
        resp.setToken(user.getToken());
        LoginResponse.UserInfo ui = new LoginResponse.UserInfo();
        ui.setId(user.getId()); ui.setUserName(user.getUserName());
        ui.setRealName(user.getRealName()); ui.setRole(user.getRole());
        ui.setImagePath(user.getImagePath());
        resp.setUserInfo(ui);
        return Result.ok(resp);
    }

    @PostMapping("/login/register")
    public Result<Void> register(@Valid @RequestBody LoginRequest request) {
        userAppService.register(request.getUserName(), request.getPassword(), RoleEnum.STUDENT.getCode());
        return Result.ok();
    }

    @GetMapping("/login/user-info")
    public Result<LoginResponse> userInfo() {
        Integer userId = getCurrentUserId();
        User user = userAppService.findById(userId);
        LoginResponse resp = new LoginResponse();
        LoginResponse.UserInfo ui = new LoginResponse.UserInfo();
        ui.setId(user.getId()); ui.setUserName(user.getUserName());
        ui.setRealName(user.getRealName()); ui.setRole(user.getRole());
        ui.setImagePath(user.getImagePath());
        resp.setUserInfo(ui);
        return Result.ok(resp);
    }

    @PostMapping("/logout")
    public Result<Void> logout() {
        Integer userId = getCurrentUserId();
        userAppService.logout(userId);
        return Result.ok();
    }
}
