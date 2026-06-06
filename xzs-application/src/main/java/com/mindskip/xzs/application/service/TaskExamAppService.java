package com.mindskip.xzs.application.service;

import com.mindskip.xzs.domain.aggregate.task.TaskExam;
import com.mindskip.xzs.domain.gateway.TaskExamGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskExamAppService {
    private final TaskExamGateway taskExamGateway;

    public TaskExamAppService(TaskExamGateway taskExamGateway) {
        this.taskExamGateway = taskExamGateway;
    }

    public TaskExam findById(Integer id) {
        return taskExamGateway.findById(id);
    }

    public List<TaskExam> findPage(Integer pageIndex, Integer pageSize, Integer gradeLevel) {
        return taskExamGateway.findPage(pageIndex, pageSize, gradeLevel);
    }

    public long count(Integer gradeLevel) {
        return taskExamGateway.count(gradeLevel);
    }

    public List<TaskExam> findStudentPage(Integer pageIndex, Integer pageSize, Integer userId) {
        return taskExamGateway.findPage(pageIndex, pageSize, null);
    }

    public long countStudent(Integer userId) {
        return taskExamGateway.count(null);
    }

    @Transactional
    public void saveOrUpdate(TaskExam taskExam) {
        if (taskExam.getId() == null) {
            taskExamGateway.save(taskExam);
        } else {
            taskExamGateway.update(taskExam);
        }
    }

    @Transactional
    public void softDelete(Integer id) {
        taskExamGateway.softDelete(id);
    }
}
