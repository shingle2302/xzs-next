package com.mindskip.xzs.domain.gateway;

import com.mindskip.xzs.domain.aggregate.task.TaskExamCustomerAnswer;

import java.util.List;

public interface TaskExamCustomerAnswerGateway {
    TaskExamCustomerAnswer findByTaskAndUser(Integer taskExamId, Integer userId);
    List<TaskExamCustomerAnswer> findByUserId(List<Integer> taskIds, Integer userId);
    void save(TaskExamCustomerAnswer answer);
    void update(TaskExamCustomerAnswer answer);
}
