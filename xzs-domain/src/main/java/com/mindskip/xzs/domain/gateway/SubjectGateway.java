package com.mindskip.xzs.domain.gateway;

import com.mindskip.xzs.domain.aggregate.subject.Subject;

import java.util.List;

public interface SubjectGateway {
    Subject findById(Integer id);
    List<Subject> findAll();
    List<Subject> findByLevel(Integer level);
    void save(Subject subject);
    void update(Subject subject);
    void softDelete(Integer id);
}
