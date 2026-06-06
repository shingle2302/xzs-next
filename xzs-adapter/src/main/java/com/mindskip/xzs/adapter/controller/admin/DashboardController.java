package com.mindskip.xzs.adapter.controller.admin;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.application.service.ExamPaperAppService;
import com.mindskip.xzs.application.service.ExamPaperAnswerAppService;
import com.mindskip.xzs.application.service.UserAppService;
import com.mindskip.xzs.exception.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/dashboard")
public class DashboardController extends BaseController {
    private final UserAppService userAppService;
    private final ExamPaperAppService examPaperAppService;
    private final ExamPaperAnswerAppService examPaperAnswerAppService;

    public DashboardController(UserAppService userAppService,
                               ExamPaperAppService examPaperAppService,
                               ExamPaperAnswerAppService examPaperAnswerAppService) {
        this.userAppService = userAppService;
        this.examPaperAppService = examPaperAppService;
        this.examPaperAnswerAppService = examPaperAnswerAppService;
    }

    @GetMapping("/index")
    public Result<Map<String, Object>> index() {
        requireRole(1, 3);
        Map<String, Object> data = new HashMap<>();
        data.put("examPaperCount", examPaperAppService.countAll());
        data.put("questionCount", examPaperAnswerAppService.countAll());
        data.put("doExamPaperCount", userAppService.countAll());
        data.put("doQuestionCount", examPaperAnswerAppService.countAll());
        return Result.ok(data);
    }
}
