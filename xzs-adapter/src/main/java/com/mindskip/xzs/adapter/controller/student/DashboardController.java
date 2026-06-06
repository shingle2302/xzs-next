package com.mindskip.xzs.adapter.controller.student;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.application.service.ExamPaperAnswerAppService;
import com.mindskip.xzs.exception.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/student/dashboard")
public class DashboardController extends BaseController {
    private final ExamPaperAnswerAppService examPaperAnswerAppService;

    public DashboardController(ExamPaperAnswerAppService examPaperAnswerAppService) {
        this.examPaperAnswerAppService = examPaperAnswerAppService;
    }

    @GetMapping("/index")
    public Result<Map<String, Object>> index() {
        Integer userId = getCurrentUserId();
        Map<String, Object> data = new HashMap<>();
        data.put("examPaperCount", examPaperAnswerAppService.count(userId, null));
        data.put("correctCount", 0L);
        return Result.ok(data);
    }
}
