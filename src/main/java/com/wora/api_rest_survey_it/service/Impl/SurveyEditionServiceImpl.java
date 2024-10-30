package com.wora.api_rest_survey_it.service.Impl;

import com.wora.api_rest_survey_it.DTO.Survey.SurveyCreateDTO;
import com.wora.api_rest_survey_it.DTO.SurveyEdition.SurveyEditionCreateDTO;
import com.wora.api_rest_survey_it.DTO.SurveyEdition.SurveyEditionResponseDTO;
import com.wora.api_rest_survey_it.entity.Survey;
import com.wora.api_rest_survey_it.entity.SurveyEdition;
import com.wora.api_rest_survey_it.mapper.SurveyEditionMapper;
import com.wora.api_rest_survey_it.repository.SurveyEditionRepository;
import com.wora.api_rest_survey_it.repository.SurveyRepository;
import com.wora.api_rest_survey_it.service.SurveyEditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyEditionServiceImpl implements SurveyEditionService {

    @Autowired
    private SurveyEditionRepository surveyEditionRepository;
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyEditionMapper surveyEditionMapper;

    @Override
    public SurveyEditionResponseDTO saveSurveyEdition(SurveyEditionCreateDTO surveyEditionCreateDTO) {
        SurveyEdition surveyEdition = surveyEditionMapper.toSurveyEdition(surveyEditionCreateDTO);
        SurveyEdition saveSurveyEdition = null;
        if (surveyEditionCreateDTO.getSurveyId() != null) {
            Survey survey = surveyRepository.findById(surveyEditionCreateDTO.getSurveyId())
                    .orElseThrow(() -> new RuntimeException("Survey id not found"));
            surveyEdition.setSurvey(survey);
            saveSurveyEdition = surveyEditionRepository.save(surveyEdition);
        }
        return surveyEditionMapper.convertToSurveyEditionResponseDTO(saveSurveyEdition);
    }
}
