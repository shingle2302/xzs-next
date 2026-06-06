package com.mindskip.xzs.adapter.controller.admin;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.adapter.dto.admin.question.QuestionEditRequest;
import com.mindskip.xzs.adapter.dto.admin.question.QuestionPageRequest;
import com.mindskip.xzs.adapter.dto.admin.question.QuestionResponse;
import com.mindskip.xzs.adapter.dto.common.PageResponse;
import com.mindskip.xzs.adapter.assembler.QuestionAssembler;
import com.mindskip.xzs.application.service.QuestionAppService;
import com.mindskip.xzs.exception.Result;
import com.mindskip.xzs.domain.aggregate.question.Question;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/question")
public class QuestionController extends BaseController {
    private final QuestionAppService questionAppService;

    public QuestionController(QuestionAppService questionAppService) {
        this.questionAppService = questionAppService;
    }

    @PostMapping("/page")
    public Result<PageResponse<QuestionResponse>> page(@Valid @RequestBody QuestionPageRequest request) {
        requireRole(1, 3);
        List<QuestionResponse> list = QuestionAssembler.INSTANCE.toResponseList(
                questionAppService.findPage(request.getPageIndex(), request.getPageSize(),
                        request.getSubjectId(), request.getQuestionType(), request.getGradeLevel()));
        long total = questionAppService.count(request.getSubjectId(), request.getQuestionType(), request.getGradeLevel());
        return Result.ok(PageResponse.of(list, total, request.getPageIndex(), request.getPageSize()));
    }

    @PostMapping("/edit")
    public Result<Void> edit(@Valid @RequestBody QuestionEditRequest request) {
        requireRole(1, 3);
        Question question = new Question(request.getQuestionType(), request.getSubjectId(), 0, getCurrentUserId());
        question.setId(request.getId());
        question.setGradeLevel(request.getGradeLevel());
        question.setDifficult(request.getDifficult());
        question.setCorrectAnswer(request.getCorrectAnswer());
        question.setScore(0);
        questionAppService.save(question);
        return Result.ok();
    }

    @GetMapping("/select/{id}")
    public Result<QuestionResponse> select(@PathVariable Integer id) {
        requireRole(1, 3);
        Question question = questionAppService.findById(id);
        return Result.ok(QuestionAssembler.INSTANCE.toResponse(question));
    }

    @PostMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        requireRole(1, 3);
        questionAppService.softDelete(id);
        return Result.ok();
    }
}
