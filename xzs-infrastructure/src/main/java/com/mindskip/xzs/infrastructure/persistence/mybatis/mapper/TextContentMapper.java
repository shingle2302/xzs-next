package com.mindskip.xzs.infrastructure.persistence.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mindskip.xzs.infrastructure.persistence.mybatis.entity.TextContentPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TextContentMapper extends BaseMapper<TextContentPo> {
}
