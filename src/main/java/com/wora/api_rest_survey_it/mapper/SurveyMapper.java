package com.wora.api_rest_survey_it.mapper;

import com.wora.api_rest_survey_it.DTO.Owner.OwnerCreateDTO;
import com.wora.api_rest_survey_it.DTO.Survey.SurveyCreateDTO;
import com.wora.api_rest_survey_it.DTO.Survey.SurveyResponseDTO;
import com.wora.api_rest_survey_it.DTO.Survey.embd.SurveyEmbdResponse;
import com.wora.api_rest_survey_it.entity.Owner;
import com.wora.api_rest_survey_it.entity.Survey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SurveyMapper {

    Survey toSurvey(SurveyCreateDTO surveyCreateDTO);

////    @Mapping(source = "owner" , target = "owner")
//    @Mapping(source = "surveyEditionList" , target = "surveyEditionList")
    SurveyResponseDTO toSurveyResponseDTO(Survey survey);
}
