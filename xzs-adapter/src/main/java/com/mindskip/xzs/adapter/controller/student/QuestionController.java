package com.mindskip.xzs.adapter.controller.student;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.adapter.dto.common.PageResponse;
import com.mindskip.xzs.adapter.dto.student.question.QuestionPageRequest;
import com.mindskip.xzs.adapter.dto.student.question.QuestionPageResponse;
import com.mindskip.xzs.adapter.assembler.QuestionAssembler;
import com.mindskip.xzs.application.service.QuestionAppService;
import com.mindskip.xzs.exception.Result;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/student/question")
public class QuestionController extends BaseController {
    private final QuestionAppService questionAppService;

    public QuestionController(QuestionAppService questionAppService) {
        this.questionAppService = questionAppService;
    }

    @PostMapping("/page")
    public Result<PageResponse<QuestionPageResponse>> page(@Valid @RequestBody QuestionPageRequest request) {
        Integer userId = getCurrentUserId();
        var list = questionAppService.findStudentPage(request.getPageIndex(), request.getPageSize(),
                request.getSubjectId(), request.getQuestionType(), userId);
        long total = questionAppService.countStudent(request.getSubjectId(), request.getQuestionType(), userId);
        List<QuestionPageResponse> responseList = list.stream()
                .map(q -> {
                    QuestionPageResponse r = new QuestionPageResponse();
                    r.setId(q.getId());
                    r.setQuestionType(q.getQuestionType());
                    r.setScore(q.getScore());
                    return r;
                })
                .collect(Collectors.toList());
        return Result.ok(PageResponse.of(responseList, total, request.getPageIndex(), request.getPageSize()));
    }

    @GetMapping("/select/{id}")
    public Result<QuestionPageResponse> select(@PathVariable Integer id) {
        var q = questionAppService.findById(id);
        QuestionPageResponse r = new QuestionPageResponse();
        r.setId(q.getId());
        r.setQuestionType(q.getQuestionType());
        r.setScore(q.getScore());
        return Result.ok(r);
    }
}
