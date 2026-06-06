package com.mindskip.xzs.infrastructure.persistence.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mindskip.xzs.infrastructure.persistence.mybatis.entity.UserPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<UserPo> {
    IPage<UserPo> findPage(Page<UserPo> page, @Param("userName") String userName, @Param("role") Integer role);

    @Select("SELECT COUNT(*) FROM \"user\" WHERE deleted = false " +
            "AND (#{userName} IS NULL OR user_name LIKE CONCAT('%', #{userName}, '%')) " +
            "AND (#{role} IS NULL OR role = #{role})")
    long count(@Param("userName") String userName, @Param("role") Integer role);

    @Select("SELECT * FROM \"user\" WHERE user_name = #{userName} AND deleted = false")
    UserPo findByUserName(@Param("userName") String userName);

    @Select("SELECT * FROM \"user\" WHERE wx_open_id = #{wxOpenId} AND deleted = false")
    UserPo findByWxOpenId(@Param("wxOpenId") String wxOpenId);

    @Select("SELECT COUNT(*) FROM \"user\" WHERE deleted = false")
    long countAll();
}
