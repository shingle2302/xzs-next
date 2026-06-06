package com.mindskip.xzs.application.service;

import com.mindskip.xzs.domain.aggregate.question.Question;
import com.mindskip.xzs.domain.gateway.QuestionGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionAppService {
    private final QuestionGateway questionGateway;

    public QuestionAppService(QuestionGateway questionGateway) {
        this.questionGateway = questionGateway;
    }

    @Transactional
    public Question save(Question question) {
        if (question.getId() == null) {
            questionGateway.save(question);
        } else {
            questionGateway.update(question);
        }
        return question;
    }

    public Question findById(Integer id) {
        return questionGateway.findById(id);
    }

    public List<Question> findByIds(List<Integer> ids) {
        return questionGateway.findByIds(ids);
    }

    public List<Question> findPage(Integer pageIndex, Integer pageSize, Integer subjectId, Integer questionType, Integer gradeLevel) {
        return questionGateway.findPage(pageIndex, pageSize, subjectId, questionType, gradeLevel);
    }

    public long count(Integer subjectId, Integer questionType, Integer gradeLevel) {
        return questionGateway.count(subjectId, questionType, gradeLevel);
    }

    public List<Question> findStudentPage(Integer pageIndex, Integer pageSize, Integer subjectId, Integer questionType, Integer userId) {
        return questionGateway.findPage(pageIndex, pageSize, subjectId, questionType, null);
    }

    public long countStudent(Integer subjectId, Integer questionType, Integer userId) {
        return questionGateway.count(subjectId, questionType, null);
    }

    @Transactional
    public void softDelete(Integer id) {
        questionGateway.softDelete(id);
    }
}
