package com.mindskip.xzs.adapter.controller.admin;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.adapter.dto.admin.exam.ExamPaperAnswerJudgeRequest;
import com.mindskip.xzs.adapter.dto.admin.exam.ExamPaperPageRequest;
import com.mindskip.xzs.adapter.dto.common.PageResponse;
import com.mindskip.xzs.adapter.dto.student.exam.ExamPaperAnswerResponse;
import com.mindskip.xzs.adapter.assembler.ExamPaperAssembler;
import com.mindskip.xzs.application.service.ExamPaperAnswerAppService;
import com.mindskip.xzs.exception.Result;
import com.mindskip.xzs.util.ExamUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

@RestController
@RequestMapping("/api/admin/exam/paper/answer")
public class ExamPaperAnswerController extends BaseController {
    private final ExamPaperAnswerAppService examPaperAnswerAppService;

    public ExamPaperAnswerController(ExamPaperAnswerAppService examPaperAnswerAppService) {
        this.examPaperAnswerAppService = examPaperAnswerAppService;
    }

    @PostMapping("/page")
    public Result<PageResponse<ExamPaperAnswerResponse>> page(@Valid @RequestBody ExamPaperPageRequest request) {
        requireRole(1, 3);
        List<ExamPaperAnswerResponse> list = ExamPaperAssembler.INSTANCE.toAnswerResponseList(
                examPaperAnswerAppService.findPage(request.getPageIndex(), request.getPageSize(), null, request.getSubjectId()));
        long total = examPaperAnswerAppService.count(null, request.getSubjectId());
        return Result.ok(PageResponse.of(list, total, request.getPageIndex(), request.getPageSize()));
    }

    @GetMapping("/select/{id}")
    public Result<ExamPaperAnswerResponse> select(@PathVariable Integer id) {
        requireRole(1, 3);
        return Result.ok(ExamPaperAssembler.INSTANCE.toAnswerResponse(examPaperAnswerAppService.findById(id)));
    }

    @PostMapping("/judge")
    public Result<String> judge(@Valid @RequestBody ExamPaperAnswerJudgeRequest request) {
        requireRole(1, 3);
        List<ExamPaperAnswerAppService.ExamAnswerJudgeItem> items = new ArrayList<>();
        if (request.getAnswerItems() != null) {
            for (ExamPaperAnswerJudgeRequest.JudgeItem item : request.getAnswerItems()) {
                items.add(new ExamPaperAnswerAppService.ExamAnswerJudgeItem(
                        item.getId(), ExamUtil.scoreFromVM(item.getScore())));
            }
        }
        String score = examPaperAnswerAppService.judge(request.getId(), items);
        return Result.ok(score);
    }
}
