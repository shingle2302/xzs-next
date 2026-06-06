package com.mindskip.xzs.domain.gateway;

import com.mindskip.xzs.domain.aggregate.question.Question;

import java.util.List;

public interface QuestionGateway {
    Question findById(Integer id);
    List<Question> findByIds(List<Integer> ids);
    List<Question> findPage(Integer pageIndex, Integer pageSize, Integer subjectId, Integer questionType, Integer gradeLevel);
    long count(Integer subjectId, Integer questionType, Integer gradeLevel);
    void save(Question question);
    void update(Question question);
    void softDelete(Integer id);
}
