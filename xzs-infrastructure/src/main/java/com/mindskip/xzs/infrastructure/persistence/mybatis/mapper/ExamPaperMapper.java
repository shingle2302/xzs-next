package com.mindskip.xzs.infrastructure.persistence.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mindskip.xzs.infrastructure.persistence.mybatis.entity.ExamPaperPo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ExamPaperMapper extends BaseMapper<ExamPaperPo> {
    @Select("SELECT * FROM exam_paper WHERE deleted = false AND task_exam_id = #{taskExamId} ORDER BY id")
    List<ExamPaperPo> findByTaskExamId(Integer taskExamId);

    @Select("SELECT COUNT(*) FROM exam_paper WHERE deleted = false")
    long countAll();

    @Select("SELECT * FROM exam_paper WHERE deleted = false ORDER BY id DESC LIMIT 10")
    List<ExamPaperPo> findRecent();
}
