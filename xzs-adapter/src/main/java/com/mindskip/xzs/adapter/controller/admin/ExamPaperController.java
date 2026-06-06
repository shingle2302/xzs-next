package com.mindskip.xzs.adapter.controller.admin;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.adapter.dto.admin.exam.ExamPaperEditRequest;
import com.mindskip.xzs.adapter.dto.admin.exam.ExamPaperPageRequest;
import com.mindskip.xzs.adapter.dto.common.PageResponse;
import com.mindskip.xzs.adapter.dto.admin.exam.ExamPaperResponse;
import com.mindskip.xzs.adapter.assembler.ExamPaperAssembler;
import com.mindskip.xzs.application.service.ExamPaperAppService;
import com.mindskip.xzs.exception.Result;
import com.mindskip.xzs.domain.aggregate.exam.ExamPaper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/exam/paper")
public class ExamPaperController extends BaseController {
    private final ExamPaperAppService examPaperAppService;

    public ExamPaperController(ExamPaperAppService examPaperAppService) {
        this.examPaperAppService = examPaperAppService;
    }

    @PostMapping("/page")
    public Result<PageResponse<ExamPaperResponse>> page(@Valid @RequestBody ExamPaperPageRequest request) {
        requireRole(1, 3);
        List<ExamPaperResponse> list = ExamPaperAssembler.INSTANCE.toResponseList(
                examPaperAppService.findPage(request.getPageIndex(), request.getPageSize(),
                        request.getSubjectId(), request.getPaperType()));
        long total = examPaperAppService.count(request.getSubjectId(), request.getPaperType());
        return Result.ok(PageResponse.of(list, total, request.getPageIndex(), request.getPageSize()));
    }

    @PostMapping("/edit")
    public Result<Void> edit(@Valid @RequestBody ExamPaperEditRequest request) {
        requireRole(1, 3);
        Integer userId = getCurrentUserId();
        ExamPaper examPaper = new ExamPaper();
        examPaper.setId(request.getId());
        examPaper.setName(request.getName());
        examPaper.setSubjectId(request.getSubjectId());
        examPaper.setPaperType(request.getPaperType());
        examPaper.setGradeLevel(request.getGradeLevel());
        examPaper.setSuggestTime(request.getSuggestTime());
        examPaper.setCreateUser(userId);
        examPaperAppService.saveOrUpdate(examPaper);
        return Result.ok();
    }

    @GetMapping("/select/{id}")
    public Result<ExamPaperResponse> select(@PathVariable Integer id) {
        requireRole(1, 3);
        ExamPaper examPaper = examPaperAppService.findById(id);
        return Result.ok(ExamPaperAssembler.INSTANCE.toResponse(examPaper));
    }

    @PostMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        requireRole(1, 3);
        examPaperAppService.softDelete(id);
        return Result.ok();
    }
}
