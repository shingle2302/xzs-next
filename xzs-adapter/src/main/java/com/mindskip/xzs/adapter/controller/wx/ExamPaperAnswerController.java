package com.mindskip.xzs.adapter.controller.wx;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.adapter.dto.student.exam.ExamAnswerSubmitRequest;
import com.mindskip.xzs.adapter.dto.student.exam.ExamPaperAnswerPageRequest;
import com.mindskip.xzs.adapter.dto.student.exam.ExamPaperAnswerResponse;
import com.mindskip.xzs.adapter.dto.common.PageResponse;
import com.mindskip.xzs.adapter.assembler.ExamPaperAssembler;
import com.mindskip.xzs.application.service.ExamPaperAnswerAppService;
import com.mindskip.xzs.exception.Result;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/wx/student/exam/paper/answer")
public class ExamPaperAnswerController extends BaseController {
    private final ExamPaperAnswerAppService examPaperAnswerAppService;

    public ExamPaperAnswerController(ExamPaperAnswerAppService examPaperAnswerAppService) {
        this.examPaperAnswerAppService = examPaperAnswerAppService;
    }

    @PostMapping("/page")
    public Result<PageResponse<ExamPaperAnswerResponse>> page(@Valid @RequestBody ExamPaperAnswerPageRequest request) {
        Integer userId = getCurrentUserId();
        List<ExamPaperAnswerResponse> list = ExamPaperAssembler.INSTANCE.toAnswerResponseList(
                examPaperAnswerAppService.findPage(request.getPageIndex(), request.getPageSize(), userId, request.getSubjectId()));
        long total = examPaperAnswerAppService.count(userId, request.getSubjectId());
        return Result.ok(PageResponse.of(list, total, request.getPageIndex(), request.getPageSize()));
    }

    @PostMapping("/answer-submit")
    public Result<Void> answerSubmit(@Valid @RequestBody ExamAnswerSubmitRequest request) {
        Integer userId = getCurrentUserId();
        List<ExamPaperAnswerAppService.ExamAnswerSubmitItem> items = new ArrayList<>();
        if (request.getAnswerItems() != null) {
            items = request.getAnswerItems().stream()
                    .map(a -> new ExamPaperAnswerAppService.ExamAnswerSubmitItem(
                            a.getQuestionId(), a.getContent(), a.getContentArray()))
                    .collect(Collectors.toList());
        }
        examPaperAnswerAppService.submit(request.getExamPaperId(), userId, request.getDoTime(), items);
        return Result.ok();
    }

    @GetMapping("/read/{id}")
    public Result<ExamPaperAnswerResponse> read(@PathVariable Integer id) {
        return Result.ok(ExamPaperAssembler.INSTANCE.toAnswerResponse(examPaperAnswerAppService.findById(id)));
    }
}
