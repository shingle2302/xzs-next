package com.mindskip.xzs.adapter.controller.admin;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.exception.Result;
import com.mindskip.xzs.application.service.SubjectAppService;
import com.mindskip.xzs.domain.aggregate.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/education")
public class EducationController extends BaseController {
    private final SubjectAppService subjectAppService;

    public EducationController(SubjectAppService subjectAppService) {
        this.subjectAppService = subjectAppService;
    }

    @GetMapping("/subject/list")
    public Result<List<Subject>> subjectList() {
        requireRole(1, 3);
        return Result.ok(subjectAppService.findAll());
    }

    @PostMapping("/subject/edit")
    public Result<Void> subjectEdit(@RequestBody Subject request) {
        requireRole(1, 3);
        subjectAppService.saveOrUpdate(request);
        return Result.ok();
    }

    @PostMapping("/subject/delete/{id}")
    public Result<Void> subjectDelete(@PathVariable Integer id) {
        requireRole(1, 3);
        subjectAppService.softDelete(id);
        return Result.ok();
    }
}
