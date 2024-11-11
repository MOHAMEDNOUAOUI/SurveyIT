package com.wora.api_rest_survey_it.mapper;


import com.wora.api_rest_survey_it.DTO.Answer.AnswerCreateDTO;
import com.wora.api_rest_survey_it.DTO.Answer.AnswerResponseDTO;
import com.wora.api_rest_survey_it.entity.Answer;
import com.wora.api_rest_survey_it.service.AnswerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    @Mapping(source = "text" , target = "text")
    Answer toAnswer(AnswerCreateDTO answerCreateDTO);

    @Mapping(source = "question" , target = "question")
    AnswerResponseDTO toResponse(Answer answer);
}
