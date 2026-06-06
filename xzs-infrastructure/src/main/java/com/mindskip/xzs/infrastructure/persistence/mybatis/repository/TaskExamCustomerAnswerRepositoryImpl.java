package com.mindskip.xzs.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mindskip.xzs.domain.aggregate.task.TaskExamCustomerAnswer;
import com.mindskip.xzs.domain.gateway.TaskExamCustomerAnswerGateway;
import com.mindskip.xzs.infrastructure.persistence.mybatis.entity.TaskExamCustomerAnswerPo;
import com.mindskip.xzs.infrastructure.persistence.mybatis.mapper.TaskExamCustomerAnswerMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TaskExamCustomerAnswerRepositoryImpl implements TaskExamCustomerAnswerGateway {
    private final TaskExamCustomerAnswerMapper mapper;

    public TaskExamCustomerAnswerRepositoryImpl(TaskExamCustomerAnswerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public TaskExamCustomerAnswer findByTaskAndUser(Integer taskExamId, Integer userId) {
        return toDomain(mapper.selectOne(
                new LambdaQueryWrapper<TaskExamCustomerAnswerPo>()
                        .eq(TaskExamCustomerAnswerPo::getTaskExamId, taskExamId)
                        .eq(TaskExamCustomerAnswerPo::getCreateUser, userId)
        ));
    }

    @Override
    public List<TaskExamCustomerAnswer> findByUserId(List<Integer> taskIds, Integer userId) {
        return mapper.selectList(
                new LambdaQueryWrapper<TaskExamCustomerAnswerPo>()
                        .in(TaskExamCustomerAnswerPo::getTaskExamId, taskIds)
                        .eq(TaskExamCustomerAnswerPo::getCreateUser, userId)
        ).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void save(TaskExamCustomerAnswer answer) {
        mapper.insert(toPo(answer));
        answer.setId(answer.getId());
    }

    @Override
    public void update(TaskExamCustomerAnswer answer) {
        mapper.updateById(toPo(answer));
    }

    private TaskExamCustomerAnswer toDomain(TaskExamCustomerAnswerPo po) {
        if (po == null) return null;
        TaskExamCustomerAnswer d = new TaskExamCustomerAnswer();
        d.setId(po.getId()); d.setTaskExamId(po.getTaskExamId());
        d.setCreateUser(po.getCreateUser()); d.setCreateTime(po.getCreateTime());
        d.setTextContentId(po.getTextContentId());
        return d;
    }

    private TaskExamCustomerAnswerPo toPo(TaskExamCustomerAnswer d) {
        if (d == null) return null;
        TaskExamCustomerAnswerPo po = new TaskExamCustomerAnswerPo();
        po.setId(d.getId()); po.setTaskExamId(d.getTaskExamId());
        po.setCreateUser(d.getCreateUser()); po.setCreateTime(d.getCreateTime());
        po.setTextContentId(d.getTextContentId());
        return po;
    }
}
