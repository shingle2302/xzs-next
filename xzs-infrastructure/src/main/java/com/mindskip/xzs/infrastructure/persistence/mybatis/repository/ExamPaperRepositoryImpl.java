package com.mindskip.xzs.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mindskip.xzs.domain.aggregate.exam.ExamPaper;
import com.mindskip.xzs.domain.gateway.ExamPaperGateway;
import com.mindskip.xzs.infrastructure.persistence.mybatis.entity.ExamPaperPo;
import com.mindskip.xzs.infrastructure.persistence.mybatis.mapper.ExamPaperMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ExamPaperRepositoryImpl implements ExamPaperGateway {
    private final ExamPaperMapper mapper;

    public ExamPaperRepositoryImpl(ExamPaperMapper mapper) { this.mapper = mapper; }

    @Override
    public ExamPaper findById(Integer id) {
        return toDomain(mapper.selectById(id));
    }

    @Override
    public List<ExamPaper> findPage(Integer pageIndex, Integer pageSize, Integer subjectId, Integer paperType) {
        var wrapper = new LambdaQueryWrapper<ExamPaperPo>()
                .eq(subjectId != null, ExamPaperPo::getSubjectId, subjectId)
                .eq(paperType != null, ExamPaperPo::getPaperType, paperType)
                .orderByDesc(ExamPaperPo::getId);
        IPage<ExamPaperPo> page = mapper.selectPage(new Page<>(pageIndex, pageSize), wrapper);
        return page.getRecords().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public long count(Integer subjectId, Integer paperType) {
        var wrapper = new LambdaQueryWrapper<ExamPaperPo>()
                .eq(subjectId != null, ExamPaperPo::getSubjectId, subjectId)
                .eq(paperType != null, ExamPaperPo::getPaperType, paperType);
        return mapper.selectCount(wrapper);
    }

    @Override
    public List<ExamPaper> findStudentPage(Integer pageIndex, Integer pageSize, Integer gradeLevel, Integer subjectId) {
        var wrapper = new LambdaQueryWrapper<ExamPaperPo>()
                .eq(gradeLevel != null, ExamPaperPo::getGradeLevel, gradeLevel)
                .eq(subjectId != null, ExamPaperPo::getSubjectId, subjectId)
                .in(ExamPaperPo::getPaperType, 1, 4)
                .orderByDesc(ExamPaperPo::getId);
        IPage<ExamPaperPo> page = mapper.selectPage(new Page<>(pageIndex, pageSize), wrapper);
        return page.getRecords().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<ExamPaper> findByTaskExamId(Integer taskExamId) {
        return mapper.findByTaskExamId(taskExamId).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public long countAll() { return mapper.countAll(); }

    @Override
    public void save(ExamPaper examPaper) {
        mapper.insert(toPo(examPaper));
        examPaper.setId(examPaper.getId());
    }

    @Override
    public void update(ExamPaper examPaper) { mapper.updateById(toPo(examPaper)); }

    @Override
    public void softDelete(Integer id) { mapper.deleteById(id); }

    private ExamPaper toDomain(ExamPaperPo po) {
        if (po == null) return null;
        ExamPaper d = new ExamPaper();
        d.setId(po.getId()); d.setName(po.getName()); d.setSubjectId(po.getSubjectId());
        d.setPaperType(po.getPaperType()); d.setGradeLevel(po.getGradeLevel());
        d.setScore(po.getScore()); d.setQuestionCount(po.getQuestionCount());
        d.setSuggestTime(po.getSuggestTime()); d.setLimitStartTime(po.getLimitStartTime());
        d.setLimitEndTime(po.getLimitEndTime()); d.setFrameTextContentId(po.getFrameTextContentId());
        d.setCreateUser(po.getCreateUser()); d.setCreateTime(po.getCreateTime());
        d.setDeleted(po.getDeleted()); d.setTaskExamId(po.getTaskExamId());
        return d;
    }

    private ExamPaperPo toPo(ExamPaper d) {
        if (d == null) return null;
        ExamPaperPo po = new ExamPaperPo();
        po.setId(d.getId()); po.setName(d.getName()); po.setSubjectId(d.getSubjectId());
        po.setPaperType(d.getPaperType()); po.setGradeLevel(d.getGradeLevel());
        po.setScore(d.getScore()); po.setQuestionCount(d.getQuestionCount());
        po.setSuggestTime(d.getSuggestTime()); po.setLimitStartTime(d.getLimitStartTime());
        po.setLimitEndTime(d.getLimitEndTime()); po.setFrameTextContentId(d.getFrameTextContentId());
        po.setCreateUser(d.getCreateUser()); po.setCreateTime(d.getCreateTime());
        po.setDeleted(d.getDeleted()); po.setTaskExamId(d.getTaskExamId());
        return po;
    }
}
