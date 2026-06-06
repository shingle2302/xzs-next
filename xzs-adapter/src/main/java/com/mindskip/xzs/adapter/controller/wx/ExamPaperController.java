package com.mindskip.xzs.adapter.controller.wx;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.adapter.dto.common.PageRequest;
import com.mindskip.xzs.adapter.dto.common.PageResponse;
import com.mindskip.xzs.adapter.dto.admin.exam.ExamPaperResponse;
import com.mindskip.xzs.adapter.assembler.ExamPaperAssembler;
import com.mindskip.xzs.application.service.ExamPaperAppService;
import com.mindskip.xzs.exception.Result;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wx/student/exam/paper")
public class ExamPaperController extends BaseController {
    private final ExamPaperAppService examPaperAppService;

    public ExamPaperController(ExamPaperAppService examPaperAppService) {
        this.examPaperAppService = examPaperAppService;
    }

    @PostMapping("/page")
    public Result<PageResponse<ExamPaperResponse>> page(@Valid @RequestBody PageRequest request) {
        Integer userId = getCurrentUserId();
        List<ExamPaperResponse> list = ExamPaperAssembler.INSTANCE.toResponseList(
                examPaperAppService.findStudentPage(request.getPageIndex(), request.getPageSize(), userId));
        long total = examPaperAppService.countStudent(userId);
        return Result.ok(PageResponse.of(list, total, request.getPageIndex(), request.getPageSize()));
    }

    @GetMapping("/select/{id}")
    public Result<ExamPaperResponse> select(@PathVariable Integer id) {
        return Result.ok(ExamPaperAssembler.INSTANCE.toResponse(examPaperAppService.findById(id)));
    }
}
