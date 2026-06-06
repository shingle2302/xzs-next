package com.mindskip.xzs.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mindskip.xzs.domain.aggregate.exam.ExamPaperQuestionCustomerAnswer;
import com.mindskip.xzs.domain.gateway.ExamPaperQuestionCustomerAnswerGateway;
import com.mindskip.xzs.infrastructure.persistence.mybatis.entity.ExamPaperQuestionCustomerAnswerPo;
import com.mindskip.xzs.infrastructure.persistence.mybatis.mapper.ExamPaperQuestionCustomerAnswerMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ExamPaperQuestionCustomerAnswerRepositoryImpl implements ExamPaperQuestionCustomerAnswerGateway {
    private final ExamPaperQuestionCustomerAnswerMapper mapper;

    public ExamPaperQuestionCustomerAnswerRepositoryImpl(ExamPaperQuestionCustomerAnswerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<ExamPaperQuestionCustomerAnswer> findByAnswerId(Integer examPaperAnswerId) {
        return mapper.selectList(
                new LambdaQueryWrapper<ExamPaperQuestionCustomerAnswerPo>()
                        .eq(ExamPaperQuestionCustomerAnswerPo::getExamPaperAnswerId, examPaperAnswerId)
        ).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<ExamPaperQuestionCustomerAnswer> findByUserId(Integer userId) {
        return mapper.selectList(
                new LambdaQueryWrapper<ExamPaperQuestionCustomerAnswerPo>()
                        .eq(ExamPaperQuestionCustomerAnswerPo::getUserId, userId)
        ).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void save(ExamPaperQuestionCustomerAnswer answer) {
        mapper.insert(toPo(answer));
        answer.setId(answer.getId());
    }

    @Override
    public void saveBatch(List<ExamPaperQuestionCustomerAnswer> answers) {
        answers.forEach(a -> mapper.insert(toPo(a)));
    }

    @Override
    public void update(ExamPaperQuestionCustomerAnswer answer) {
        mapper.updateById(toPo(answer));
    }

    private ExamPaperQuestionCustomerAnswer toDomain(ExamPaperQuestionCustomerAnswerPo po) {
        if (po == null) return null;
        ExamPaperQuestionCustomerAnswer d = new ExamPaperQuestionCustomerAnswer();
        d.setId(po.getId()); d.setQuestionId(po.getQuestionId());
        d.setExamPaperId(po.getExamPaperId()); d.setExamPaperAnswerId(po.getExamPaperAnswerId());
        d.setSubjectId(po.getSubjectId()); d.setUserId(po.getUserId());
        d.setQuestionType(po.getQuestionType()); d.setScore(po.getCustomerScore());
        d.setQuestionScore(po.getQuestionScore()); d.setQuestionTextContentId(po.getQuestionTextContentId());
        d.setAnswer(po.getAnswer()); d.setTextContentId(po.getTextContentId());
        d.setDoRight(po.getDoRight()); d.setItemOrder(po.getItemOrder()); d.setCreateTime(po.getCreateTime());
        return d;
    }

    private ExamPaperQuestionCustomerAnswerPo toPo(ExamPaperQuestionCustomerAnswer d) {
        if (d == null) return null;
        ExamPaperQuestionCustomerAnswerPo po = new ExamPaperQuestionCustomerAnswerPo();
        po.setId(d.getId()); po.setQuestionId(d.getQuestionId());
        po.setExamPaperId(d.getExamPaperId()); po.setExamPaperAnswerId(d.getExamPaperAnswerId());
        po.setSubjectId(d.getSubjectId()); po.setUserId(d.getUserId());
        po.setQuestionType(d.getQuestionType()); po.setCustomerScore(d.getScore());
        po.setQuestionScore(d.getQuestionScore()); po.setQuestionTextContentId(d.getQuestionTextContentId());
        po.setAnswer(d.getAnswer()); po.setTextContentId(d.getTextContentId());
        po.setDoRight(d.getDoRight()); po.setItemOrder(d.getItemOrder()); po.setCreateTime(d.getCreateTime());
        return po;
    }
}
