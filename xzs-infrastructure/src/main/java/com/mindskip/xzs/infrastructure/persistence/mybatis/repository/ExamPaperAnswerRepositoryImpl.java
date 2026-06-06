package com.mindskip.xzs.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mindskip.xzs.domain.aggregate.exam.ExamPaperAnswer;
import com.mindskip.xzs.domain.gateway.ExamPaperAnswerGateway;
import com.mindskip.xzs.infrastructure.persistence.mybatis.entity.ExamPaperAnswerPo;
import com.mindskip.xzs.infrastructure.persistence.mybatis.mapper.ExamPaperAnswerMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ExamPaperAnswerRepositoryImpl implements ExamPaperAnswerGateway {
    private final ExamPaperAnswerMapper mapper;

    public ExamPaperAnswerRepositoryImpl(ExamPaperAnswerMapper mapper) { this.mapper = mapper; }

    @Override
    public ExamPaperAnswer findById(Integer id) { return toDomain(mapper.selectById(id)); }

    @Override
    public List<ExamPaperAnswer> findByUserId(Integer userId) {
        var wrapper = new LambdaQueryWrapper<ExamPaperAnswerPo>()
                .eq(ExamPaperAnswerPo::getUserId, userId).orderByDesc(ExamPaperAnswerPo::getId);
        return mapper.selectList(wrapper).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<ExamPaperAnswer> findPage(Integer pageIndex, Integer pageSize, Integer userId, Integer subjectId) {
        var wrapper = new LambdaQueryWrapper<ExamPaperAnswerPo>()
                .eq(userId != null, ExamPaperAnswerPo::getUserId, userId)
                .eq(subjectId != null, ExamPaperAnswerPo::getSubjectId, subjectId)
                .orderByDesc(ExamPaperAnswerPo::getId);
        IPage<ExamPaperAnswerPo> page = mapper.selectPage(new Page<>(pageIndex, pageSize), wrapper);
        return page.getRecords().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public long count(Integer userId, Integer subjectId) {
        var wrapper = new LambdaQueryWrapper<ExamPaperAnswerPo>()
                .eq(userId != null, ExamPaperAnswerPo::getUserId, userId)
                .eq(subjectId != null, ExamPaperAnswerPo::getSubjectId, subjectId);
        return mapper.selectCount(wrapper);
    }

    @Override
    public ExamPaperAnswer findByPaperAndUser(Integer examPaperId, Integer userId) {
        return toDomain(mapper.findByPaperAndUser(examPaperId, userId));
    }

    @Override
    public void save(ExamPaperAnswer answer) {
        mapper.insert(toPo(answer));
        answer.setId(answer.getId());
    }

    @Override
    public void update(ExamPaperAnswer answer) { mapper.updateById(toPo(answer)); }

    private ExamPaperAnswer toDomain(ExamPaperAnswerPo po) {
        if (po == null) return null;
        ExamPaperAnswer d = new ExamPaperAnswer();
        d.setId(po.getId()); d.setExamPaperId(po.getExamPaperId());
        d.setPaperName(po.getPaperName()); d.setPaperType(po.getPaperType());
        d.setSubjectId(po.getSubjectId()); d.setUserId(po.getUserId());
        d.setUserName(po.getUserName()); d.setPaperScore(po.getPaperScore());
        d.setQuestionCorrect(po.getQuestionCorrect()); d.setQuestionCount(po.getQuestionCount());
        d.setSystemScore(po.getSystemScore()); d.setUserScore(po.getUserScore());
        d.setStatus(po.getStatus()); d.setTaskExamId(po.getTaskExamId());
        d.setDoTime(po.getDoTime()); d.setCreateTime(po.getCreateTime());
        return d;
    }

    private ExamPaperAnswerPo toPo(ExamPaperAnswer d) {
        if (d == null) return null;
        ExamPaperAnswerPo po = new ExamPaperAnswerPo();
        po.setId(d.getId()); po.setExamPaperId(d.getExamPaperId());
        po.setPaperName(d.getPaperName()); po.setPaperType(d.getPaperType());
        po.setSubjectId(d.getSubjectId()); po.setUserId(d.getUserId());
        po.setUserName(d.getUserName()); po.setPaperScore(d.getPaperScore());
        po.setQuestionCorrect(d.getQuestionCorrect()); po.setQuestionCount(d.getQuestionCount());
        po.setSystemScore(d.getSystemScore()); po.setUserScore(d.getUserScore());
        po.setStatus(d.getStatus()); po.setTaskExamId(d.getTaskExamId());
        po.setDoTime(d.getDoTime()); po.setCreateTime(d.getCreateTime());
        return po;
    }
}
