package com.mindskip.xzs.adapter.controller.student;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.adapter.dto.common.PageRequest;
import com.mindskip.xzs.adapter.dto.common.PageResponse;
import com.mindskip.xzs.application.service.TaskExamAppService;
import com.mindskip.xzs.exception.Result;
import com.mindskip.xzs.domain.aggregate.task.TaskExam;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/task")
public class TaskExamController extends BaseController {
    private final TaskExamAppService taskExamAppService;

    public TaskExamController(TaskExamAppService taskExamAppService) {
        this.taskExamAppService = taskExamAppService;
    }

    @PostMapping("/page")
    public Result<PageResponse<TaskExam>> page(@Valid @RequestBody PageRequest request) {
        Integer userId = getCurrentUserId();
        List<TaskExam> list = taskExamAppService.findStudentPage(request.getPageIndex(), request.getPageSize(), userId);
        long total = taskExamAppService.countStudent(userId);
        return Result.ok(PageResponse.of(list, total, request.getPageIndex(), request.getPageSize()));
    }

    @GetMapping("/select/{id}")
    public Result<TaskExam> select(@PathVariable Integer id) {
        return Result.ok(taskExamAppService.findById(id));
    }
}
