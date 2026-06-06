package com.mindskip.xzs.infrastructure.persistence.mybatis.repository;

import com.mindskip.xzs.domain.aggregate.message.TextContent;
import com.mindskip.xzs.domain.gateway.TextContentGateway;
import com.mindskip.xzs.infrastructure.persistence.mybatis.entity.TextContentPo;
import com.mindskip.xzs.infrastructure.persistence.mybatis.mapper.TextContentMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TextContentRepositoryImpl implements TextContentGateway {
    private final TextContentMapper mapper;

    public TextContentRepositoryImpl(TextContentMapper mapper) { this.mapper = mapper; }

    @Override
    public TextContent findById(Integer id) {
        return toDomain(mapper.selectById(id));
    }

    @Override
    public void save(TextContent textContent) {
        mapper.insert(toPo(textContent));
        textContent.setId(textContent.getId());
    }

    @Override
    public void update(TextContent textContent) {
        mapper.updateById(toPo(textContent));
    }

    private TextContent toDomain(TextContentPo po) {
        if (po == null) return null;
        TextContent d = new TextContent();
        d.setId(po.getId());
        d.setContent(po.getContent());
        d.setCreateTime(po.getCreateTime());
        return d;
    }

    private TextContentPo toPo(TextContent d) {
        if (d == null) return null;
        TextContentPo po = new TextContentPo();
        po.setId(d.getId());
        po.setContent(d.getContent());
        po.setCreateTime(d.getCreateTime());
        return po;
    }
}
