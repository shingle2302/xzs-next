package com.mindskip.xzs.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mindskip.xzs.domain.aggregate.subject.Subject;
import com.mindskip.xzs.domain.gateway.SubjectGateway;
import com.mindskip.xzs.infrastructure.persistence.mybatis.entity.SubjectPo;
import com.mindskip.xzs.infrastructure.persistence.mybatis.mapper.SubjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SubjectRepositoryImpl implements SubjectGateway {
    private final SubjectMapper mapper;

    public SubjectRepositoryImpl(SubjectMapper mapper) { this.mapper = mapper; }

    @Override
    public Subject findById(Integer id) { return toDomain(mapper.selectById(id)); }

    @Override
    public List<Subject> findAll() {
        return mapper.selectList(null).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Subject> findByLevel(Integer level) {
        var wrapper = new LambdaQueryWrapper<SubjectPo>().eq(SubjectPo::getLevel, level);
        return mapper.selectList(wrapper).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void save(Subject subject) { mapper.insert(toPo(subject)); }

    @Override
    public void update(Subject subject) { mapper.updateById(toPo(subject)); }

    @Override
    public void softDelete(Integer id) { mapper.deleteById(id); }

    private Subject toDomain(SubjectPo po) {
        if (po == null) return null;
        Subject d = new Subject(); d.setId(po.getId()); d.setName(po.getName());
        d.setLevel(po.getLevel()); d.setLevel1(po.getLevel1()); d.setLevel2(po.getLevel2());
        d.setCreateTime(po.getCreateTime()); d.setDeleted(po.getDeleted());
        return d;
    }

    private SubjectPo toPo(Subject d) {
        if (d == null) return null;
        SubjectPo po = new SubjectPo(); po.setId(d.getId()); po.setName(d.getName());
        po.setLevel(d.getLevel()); po.setLevel1(d.getLevel1()); po.setLevel2(d.getLevel2());
        po.setCreateTime(d.getCreateTime()); po.setDeleted(d.getDeleted());
        return po;
    }
}
