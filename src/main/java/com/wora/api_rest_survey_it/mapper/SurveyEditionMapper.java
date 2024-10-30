package com.wora.api_rest_survey_it.mapper;

import com.wora.api_rest_survey_it.DTO.SurveyEdition.SurveyEditionCreateDTO;
import com.wora.api_rest_survey_it.DTO.SurveyEdition.SurveyEditionResponseDTO;
import com.wora.api_rest_survey_it.entity.SurveyEdition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SurveyEditionMapper {


    SurveyEdition toSurveyEdition(SurveyEditionCreateDTO surveyEditionCreateDTO);

    @Mapping(source = "survey" , target = "survey")
    SurveyEditionResponseDTO convertToSurveyEditionResponseDTO(SurveyEdition surveyEdition);

}
