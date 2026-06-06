package com.mindskip.xzs.adapter.controller.admin;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.adapter.dto.admin.task.TaskExamRequest;
import com.mindskip.xzs.adapter.dto.common.PageRequest;
import com.mindskip.xzs.adapter.dto.common.PageResponse;
import com.mindskip.xzs.exception.Result;
import com.mindskip.xzs.application.service.TaskExamAppService;
import com.mindskip.xzs.domain.aggregate.task.TaskExam;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin/task")
public class TaskExamController extends BaseController {
    private final TaskExamAppService taskExamAppService;

    public TaskExamController(TaskExamAppService taskExamAppService) {
        this.taskExamAppService = taskExamAppService;
    }

    @PostMapping("/page")
    public Result<PageResponse<TaskExam>> page(@Valid @RequestBody PageRequest request) {
        requireRole(1, 3);
        List<TaskExam> list = taskExamAppService.findPage(request.getPageIndex(), request.getPageSize(), null);
        long total = taskExamAppService.count(null);
        return Result.ok(PageResponse.of(list, total, request.getPageIndex(), request.getPageSize()));
    }

    @PostMapping("/edit")
    public Result<Void> edit(@Valid @RequestBody TaskExamRequest request) {
        requireRole(1, 3);
        Integer userId = getCurrentUserId();
        TaskExam taskExam = new TaskExam();
        taskExam.setId(request.getId());
        taskExam.setTitle(request.getTitle());
        taskExam.setGradeLevel(request.getGradeLevel());
        taskExam.setCreateUser(userId);
        taskExam.setCreateTime(LocalDateTime.now());
        taskExamAppService.saveOrUpdate(taskExam);
        return Result.ok();
    }

    @GetMapping("/select/{id}")
    public Result<TaskExam> select(@PathVariable Integer id) {
        requireRole(1, 3);
        return Result.ok(taskExamAppService.findById(id));
    }

    @PostMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        requireRole(1, 3);
        taskExamAppService.softDelete(id);
        return Result.ok();
    }
}
