package com.mindskip.xzs.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mindskip.xzs.domain.aggregate.task.TaskExam;
import com.mindskip.xzs.domain.gateway.TaskExamGateway;
import com.mindskip.xzs.infrastructure.persistence.mybatis.entity.TaskExamPo;
import com.mindskip.xzs.infrastructure.persistence.mybatis.mapper.TaskExamMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TaskExamRepositoryImpl implements TaskExamGateway {
    private final TaskExamMapper mapper;

    public TaskExamRepositoryImpl(TaskExamMapper mapper) { this.mapper = mapper; }

    @Override
    public TaskExam findById(Integer id) { return toDomain(mapper.selectById(id)); }

    @Override
    public List<TaskExam> findPage(Integer pageIndex, Integer pageSize, Integer gradeLevel) {
        var wrapper = new LambdaQueryWrapper<TaskExamPo>()
                .eq(gradeLevel != null, TaskExamPo::getGradeLevel, gradeLevel)
                .orderByDesc(TaskExamPo::getId);
        IPage<TaskExamPo> page = mapper.selectPage(new Page<>(pageIndex, pageSize), wrapper);
        return page.getRecords().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public long count(Integer gradeLevel) {
        var wrapper = new LambdaQueryWrapper<TaskExamPo>()
                .eq(gradeLevel != null, TaskExamPo::getGradeLevel, gradeLevel);
        return mapper.selectCount(wrapper);
    }

    @Override
    public void save(TaskExam taskExam) { mapper.insert(toPo(taskExam)); }

    @Override
    public void update(TaskExam taskExam) { mapper.updateById(toPo(taskExam)); }

    @Override
    public void softDelete(Integer id) { mapper.deleteById(id); }

    private TaskExam toDomain(TaskExamPo po) {
        if (po == null) return null;
        TaskExam d = new TaskExam(); d.setId(po.getId()); d.setTitle(po.getTitle());
        d.setGradeLevel(po.getGradeLevel()); d.setFrameTextContentId(po.getFrameTextContentId());
        d.setCreateUser(po.getCreateUser()); d.setCreateTime(po.getCreateTime());
        d.setDeleted(po.getDeleted());
        return d;
    }

    private TaskExamPo toPo(TaskExam d) {
        if (d == null) return null;
        TaskExamPo po = new TaskExamPo(); po.setId(d.getId()); po.setTitle(d.getTitle());
        po.setGradeLevel(d.getGradeLevel()); po.setFrameTextContentId(d.getFrameTextContentId());
        po.setCreateUser(d.getCreateUser()); po.setCreateTime(d.getCreateTime());
        po.setDeleted(d.getDeleted());
        return po;
    }
}
