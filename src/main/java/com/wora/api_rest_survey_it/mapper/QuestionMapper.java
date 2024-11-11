package com.wora.api_rest_survey_it.mapper;

import com.wora.api_rest_survey_it.DTO.Question.QuestionCreateDTO;
import com.wora.api_rest_survey_it.DTO.Question.QuestionResponseDTO;
import com.wora.api_rest_survey_it.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(source = "text" , target = "text")
    Question toQuestion(QuestionCreateDTO questionCreateDTO);

    @Mapping(source = "answerList" , target = "answerList")
    QuestionResponseDTO toResponse(Question question);

}
