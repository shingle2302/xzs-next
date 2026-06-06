package com.mindskip.xzs.adapter.controller.admin;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.adapter.dto.admin.user.UserCreateRequest;
import com.mindskip.xzs.adapter.dto.admin.user.UserPageRequest;
import com.mindskip.xzs.adapter.dto.admin.user.UserResponse;
import com.mindskip.xzs.adapter.dto.common.PageResponse;
import com.mindskip.xzs.adapter.assembler.UserAssembler;
import com.mindskip.xzs.application.service.UserAppService;
import com.mindskip.xzs.exception.Result;
import com.mindskip.xzs.domain.aggregate.user.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/user")
public class UserController extends BaseController {
    private final UserAppService userAppService;

    public UserController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @PostMapping("/page")
    public Result<PageResponse<UserResponse>> page(@Valid @RequestBody UserPageRequest request) {
        requireRole(1, 3);
        List<UserResponse> list = UserAssembler.INSTANCE.toResponseList(
                userAppService.findPage(request.getPageIndex(), request.getPageSize(),
                        request.getUserName(), request.getRole()));
        long total = userAppService.count(request.getUserName(), request.getRole());
        return Result.ok(PageResponse.of(list, total, request.getPageIndex(), request.getPageSize()));
    }

    @PostMapping("/edit")
    public Result<Void> edit(@Valid @RequestBody UserCreateRequest request) {
        requireRole(1, 3);
        User user = new User(request.getUserName(), request.getPassword(), request.getRole());
        user.setRealName(request.getRealName());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        user.setSex(request.getSex());
        userAppService.createUser(user);
        return Result.ok();
    }

    @GetMapping("/select/{id}")
    public Result<UserResponse> select(@PathVariable Integer id) {
        requireRole(1, 3);
        User user = userAppService.findById(id);
        return Result.ok(UserAssembler.INSTANCE.toResponse(user));
    }

    @PostMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        requireRole(1, 3);
        userAppService.softDelete(id);
        return Result.ok();
    }

    @PostMapping("/change-status/{id}")
    public Result<Void> changeStatus(@PathVariable Integer id) {
        requireRole(1, 3);
        userAppService.changeStatus(id);
        return Result.ok();
    }
}
