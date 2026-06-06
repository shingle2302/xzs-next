package com.mindskip.xzs.adapter.assembler;

import com.mindskip.xzs.adapter.dto.admin.question.QuestionResponse;
import com.mindskip.xzs.domain.aggregate.question.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface QuestionAssembler {
    QuestionAssembler INSTANCE = Mappers.getMapper(QuestionAssembler.class);

    @Mapping(target = "title", ignore = true)
    @Mapping(target = "analyze", ignore = true)
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    QuestionResponse toResponse(Question question);

    List<QuestionResponse> toResponseList(List<Question> questions);
}
