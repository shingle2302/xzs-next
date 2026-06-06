package com.mindskip.xzs.infrastructure.persistence.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mindskip.xzs.infrastructure.persistence.mybatis.entity.ExamPaperAnswerPo;
import org.apache.ibatis.annotations.Select;

public interface ExamPaperAnswerMapper extends BaseMapper<ExamPaperAnswerPo> {
    @Select("SELECT * FROM exam_paper_answer WHERE exam_paper_id = #{examPaperId} AND user_id = #{userId}")
    ExamPaperAnswerPo findByPaperAndUser(Integer examPaperId, Integer userId);
}
