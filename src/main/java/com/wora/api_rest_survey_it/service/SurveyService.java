package com.wora.api_rest_survey_it.service;

import com.wora.api_rest_survey_it.DTO.Survey.SurveyCreateDTO;
import com.wora.api_rest_survey_it.DTO.Survey.SurveyResponseDTO;
import com.wora.api_rest_survey_it.entity.Survey;

import java.util.List;

public interface SurveyService {

    SurveyResponseDTO saveSurvey(SurveyCreateDTO surveyCreateDTO);
    List<SurveyResponseDTO> getAllSurveys();
}
