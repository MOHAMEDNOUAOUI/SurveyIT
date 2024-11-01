package com.wora.api_rest_survey_it.service;

import com.wora.api_rest_survey_it.DTO.Survey.SurveyCreateDTO;
import com.wora.api_rest_survey_it.DTO.SurveyEdition.SurveyEditionCreateDTO;
import com.wora.api_rest_survey_it.DTO.SurveyEdition.SurveyEditionResponseDTO;
import com.wora.api_rest_survey_it.entity.SurveyEdition;
import com.wora.api_rest_survey_it.repository.SurveyEditionRepository;

import java.util.List;

public interface SurveyEditionService {

    SurveyEditionResponseDTO saveSurveyEdition(SurveyEditionCreateDTO surveyEditionCreateDTO);

    List<SurveyEditionResponseDTO> getAllSurveyEditions();

    SurveyEditionResponseDTO findSurveyEditionById(Long id);

    boolean deleteSurveyEditionById(Long id);

    SurveyEditionResponseDTO updateSurveyEdition(Long id , SurveyEditionCreateDTO surveyEditionCreateDTO);

}
