package com.mindskip.xzs.adapter.assembler;

import com.mindskip.xzs.adapter.dto.admin.exam.ExamPaperResponse;
import com.mindskip.xzs.adapter.dto.student.exam.ExamPaperAnswerResponse;
import com.mindskip.xzs.domain.aggregate.exam.ExamPaper;
import com.mindskip.xzs.domain.aggregate.exam.ExamPaperAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExamPaperAssembler {
    ExamPaperAssembler INSTANCE = Mappers.getMapper(ExamPaperAssembler.class);

    @Mapping(target = "subjectName", ignore = true)
    @Mapping(target = "titleItems", ignore = true)
    ExamPaperResponse toResponse(ExamPaper examPaper);

    List<ExamPaperResponse> toResponseList(List<ExamPaper> examPapers);

    @Mapping(target = "questionResponses", ignore = true)
    ExamPaperAnswerResponse toAnswerResponse(ExamPaperAnswer answer);

    List<ExamPaperAnswerResponse> toAnswerResponseList(List<ExamPaperAnswer> answers);
}
