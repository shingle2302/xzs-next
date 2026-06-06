package com.mindskip.xzs.adapter.controller.student;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.adapter.dto.common.PageResponse;
import com.mindskip.xzs.adapter.dto.student.question.QuestionPageRequest;
import com.mindskip.xzs.adapter.dto.student.question.QuestionPageResponse;
import com.mindskip.xzs.application.service.ExamPaperAnswerAppService;
import com.mindskip.xzs.exception.Result;
import com.mindskip.xzs.domain.aggregate.exam.ExamPaperAnswer;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/student/question/answer")
public class QuestionAnswerController extends BaseController {
    private final ExamPaperAnswerAppService examPaperAnswerAppService;

    public QuestionAnswerController(ExamPaperAnswerAppService examPaperAnswerAppService) {
        this.examPaperAnswerAppService = examPaperAnswerAppService;
    }

    @PostMapping("/page")
    public Result<PageResponse<QuestionPageResponse>> page(@Valid @RequestBody QuestionPageRequest request) {
        Integer userId = getCurrentUserId();
        var list = examPaperAnswerAppService.findPage(request.getPageIndex(), request.getPageSize(), userId, request.getSubjectId());
        long total = examPaperAnswerAppService.count(userId, request.getSubjectId());
        List<QuestionPageResponse> responseList = list.stream()
                .map(a -> {
                    QuestionPageResponse r = new QuestionPageResponse();
                    r.setId(a.getExamPaperId());
                    r.setScore(a.getPaperScore());
                    r.setCreateTime(a.getCreateTime() != null ? a.getCreateTime().toString() : null);
                    return r;
                })
                .collect(Collectors.toList());
        return Result.ok(PageResponse.of(responseList, total, request.getPageIndex(), request.getPageSize()));
    }

    @GetMapping("/select/{id}")
    public Result<ExamPaperAnswer> select(@PathVariable Integer id) {
        Integer userId = getCurrentUserId();
        return Result.ok(examPaperAnswerAppService.findById(id));
    }
}
