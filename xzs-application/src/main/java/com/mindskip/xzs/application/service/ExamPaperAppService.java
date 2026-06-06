package com.mindskip.xzs.application.service;

import com.mindskip.xzs.domain.aggregate.exam.ExamPaper;
import com.mindskip.xzs.domain.aggregate.message.TextContent;
import com.mindskip.xzs.domain.gateway.ExamPaperGateway;
import com.mindskip.xzs.domain.gateway.TextContentGateway;
import com.mindskip.xzs.domain.service.ExamPaperDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamPaperAppService {
    private final ExamPaperGateway examPaperGateway;
    private final TextContentGateway textContentGateway;
    private final ExamPaperDomainService examPaperDomainService;

    public ExamPaperAppService(ExamPaperGateway examPaperGateway, TextContentGateway textContentGateway) {
        this.examPaperGateway = examPaperGateway;
        this.textContentGateway = textContentGateway;
        this.examPaperDomainService = new ExamPaperDomainService();
    }

    @Transactional
    public ExamPaper save(ExamPaper examPaper) {
        examPaperDomainService.validateBeforeSave(examPaper);
        if (examPaper.getId() == null) {
            examPaperGateway.save(examPaper);
        } else {
            examPaperGateway.update(examPaper);
        }
        return examPaper;
    }

    public ExamPaper findById(Integer id) {
        return examPaperGateway.findById(id);
    }

    public List<ExamPaper> findPage(Integer pageIndex, Integer pageSize, Integer subjectId, Integer paperType) {
        return examPaperGateway.findPage(pageIndex, pageSize, subjectId, paperType);
    }

    public long count(Integer subjectId, Integer paperType) {
        return examPaperGateway.count(subjectId, paperType);
    }

    public long countAll() {
        return examPaperGateway.countAll();
    }

    public List<ExamPaper> findStudentPage(Integer pageIndex, Integer pageSize, Integer userId) {
        return examPaperGateway.findStudentPage(pageIndex, pageSize, null, null);
    }

    public long countStudent(Integer userId) {
        return examPaperGateway.count(null, null);
    }

    @Transactional
    public void softDelete(Integer id) {
        examPaperGateway.softDelete(id);
    }

    @Transactional
    public void saveOrUpdate(ExamPaper examPaper) {
        examPaperDomainService.validateBeforeSave(examPaper);
        if (examPaper.getId() == null) {
            examPaperGateway.save(examPaper);
        } else {
            examPaperGateway.update(examPaper);
        }
    }
}
