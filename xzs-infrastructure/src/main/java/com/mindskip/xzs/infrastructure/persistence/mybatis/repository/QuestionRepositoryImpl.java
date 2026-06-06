package com.mindskip.xzs.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mindskip.xzs.domain.aggregate.question.Question;
import com.mindskip.xzs.domain.gateway.QuestionGateway;
import com.mindskip.xzs.infrastructure.persistence.mybatis.entity.QuestionPo;
import com.mindskip.xzs.infrastructure.persistence.mybatis.mapper.QuestionMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class QuestionRepositoryImpl implements QuestionGateway {
    private final QuestionMapper mapper;

    public QuestionRepositoryImpl(QuestionMapper mapper) { this.mapper = mapper; }

    @Override
    public Question findById(Integer id) { return toDomain(mapper.selectById(id)); }

    @Override
    public List<Question> findByIds(List<Integer> ids) {
        return mapper.selectBatchIds(ids).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Question> findPage(Integer pageIndex, Integer pageSize, Integer subjectId, Integer questionType, Integer gradeLevel) {
        var wrapper = new LambdaQueryWrapper<QuestionPo>()
                .eq(subjectId != null, QuestionPo::getSubjectId, subjectId)
                .eq(questionType != null, QuestionPo::getQuestionType, questionType)
                .eq(gradeLevel != null, QuestionPo::getGradeLevel, gradeLevel)
                .orderByDesc(QuestionPo::getId);
        IPage<QuestionPo> page = mapper.selectPage(new Page<>(pageIndex, pageSize), wrapper);
        return page.getRecords().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public long count(Integer subjectId, Integer questionType, Integer gradeLevel) {
        var wrapper = new LambdaQueryWrapper<QuestionPo>()
                .eq(subjectId != null, QuestionPo::getSubjectId, subjectId)
                .eq(questionType != null, QuestionPo::getQuestionType, questionType)
                .eq(gradeLevel != null, QuestionPo::getGradeLevel, gradeLevel);
        return mapper.selectCount(wrapper);
    }

    @Override
    public void save(Question question) { mapper.insert(toPo(question)); }

    @Override
    public void update(Question question) { mapper.updateById(toPo(question)); }

    @Override
    public void softDelete(Integer id) { mapper.deleteById(id); }

    private Question toDomain(QuestionPo po) {
        if (po == null) return null;
        Question d = new Question();
        d.setId(po.getId()); d.setQuestionType(po.getQuestionType()); d.setSubjectId(po.getSubjectId());
        d.setScore(po.getScore()); d.setGradeLevel(po.getGradeLevel()); d.setDifficult(po.getDifficult());
        d.setCorrectAnswer(po.getCorrectAnswer()); d.setAnalyzeTextContentId(po.getAnalyzeTextContentId());
        d.setInfoTextContentId(po.getInfoTextContentId()); d.setCreateUser(po.getCreateUser());
        d.setStatus(po.getStatus()); d.setCreateTime(po.getCreateTime()); d.setDeleted(po.getDeleted());
        return d;
    }

    private QuestionPo toPo(Question d) {
        if (d == null) return null;
        QuestionPo po = new QuestionPo();
        po.setId(d.getId()); po.setQuestionType(d.getQuestionType()); po.setSubjectId(d.getSubjectId());
        po.setScore(d.getScore()); po.setGradeLevel(d.getGradeLevel()); po.setDifficult(d.getDifficult());
        po.setCorrectAnswer(d.getCorrectAnswer()); po.setAnalyzeTextContentId(d.getAnalyzeTextContentId());
        po.setInfoTextContentId(d.getInfoTextContentId()); po.setCreateUser(d.getCreateUser());
        po.setStatus(d.getStatus()); po.setCreateTime(d.getCreateTime()); po.setDeleted(d.getDeleted());
        return po;
    }
}
