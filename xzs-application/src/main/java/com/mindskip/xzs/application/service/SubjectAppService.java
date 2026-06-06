package com.mindskip.xzs.application.service;

import com.mindskip.xzs.domain.aggregate.subject.Subject;
import com.mindskip.xzs.domain.gateway.SubjectGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectAppService {
    private final SubjectGateway subjectGateway;

    public SubjectAppService(SubjectGateway subjectGateway) {
        this.subjectGateway = subjectGateway;
    }

    public List<Subject> findAll() {
        return subjectGateway.findAll();
    }

    public Subject findById(Integer id) {
        return subjectGateway.findById(id);
    }

    @Transactional
    public Subject save(Subject subject) {
        if (subject.getId() == null) {
            subjectGateway.save(subject);
        } else {
            subjectGateway.update(subject);
        }
        return subject;
    }

    @Transactional
    public void softDelete(Integer id) {
        subjectGateway.softDelete(id);
    }

    @Transactional
    public void saveOrUpdate(Subject subject) {
        if (subject.getId() == null) {
            subjectGateway.save(subject);
        } else {
            subjectGateway.update(subject);
        }
    }
}
