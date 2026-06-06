package com.mindskip.xzs.domain.service;

import com.mindskip.xzs.domain.aggregate.task.TaskExam;

public class TaskDomainService {

    public void validateBeforeSave(TaskExam taskExam) {
        if (taskExam.getTitle() == null || taskExam.getTitle().isBlank()) {
            throw new IllegalArgumentException("任务名称不能为空");
        }
        if (taskExam.getGradeLevel() == null) {
            throw new IllegalArgumentException("请选择年级");
        }
        if (taskExam.getCreateUser() == null) {
            throw new IllegalArgumentException("请指定创建人");
        }
    }

    public boolean canStudentAccess(TaskExam taskExam, Integer gradeLevel) {
        if (taskExam.getDeleted() != null && taskExam.getDeleted()) {
            return false;
        }
        if (taskExam.getGradeLevel() != null && gradeLevel != null
                && !taskExam.getGradeLevel().equals(gradeLevel)) {
            return false;
        }
        return true;
    }
}
