package com.mindskip.xzs.domain.gateway;

import com.mindskip.xzs.domain.aggregate.task.TaskExam;

import java.util.List;

public interface TaskExamGateway {
    TaskExam findById(Integer id);
    List<TaskExam> findPage(Integer pageIndex, Integer pageSize, Integer gradeLevel);
    long count(Integer gradeLevel);
    void save(TaskExam taskExam);
    void update(TaskExam taskExam);
    void softDelete(Integer id);
}
