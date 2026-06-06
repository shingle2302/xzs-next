package com.mindskip.xzs.adapter.controller.student;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.exception.Result;
import com.mindskip.xzs.application.service.SubjectAppService;
import com.mindskip.xzs.domain.aggregate.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/education")
public class EducationController extends BaseController {
    private final SubjectAppService subjectAppService;

    public EducationController(SubjectAppService subjectAppService) {
        this.subjectAppService = subjectAppService;
    }

    @GetMapping("/subject/list")
    public Result<List<Subject>> subjectList() {
        return Result.ok(subjectAppService.findAll());
    }
}
