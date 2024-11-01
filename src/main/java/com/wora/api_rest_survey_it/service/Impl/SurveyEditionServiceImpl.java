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
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<SurveyEditionResponseDTO> getAllSurveyEditions() {
        List<SurveyEdition> surveyEditionList = surveyEditionRepository.findAll();
        if (surveyEditionList.isEmpty()){
            throw new RuntimeException("No Surveys Edition Found");
        }
        return surveyEditionList.stream().map(surveyEditionMapper::convertToSurveyEditionResponseDTO).toList();
    }

    @Override
    public SurveyEditionResponseDTO findSurveyEditionById(Long id) {
        if(surveyEditionRepository.existsById(id)){
            SurveyEdition surveyEdition = surveyEditionRepository.findById(id).get();
            return surveyEditionMapper.convertToSurveyEditionResponseDTO(surveyEdition);
        }else{
            throw new EntityNotFoundException("Couldnt find a survey edition with the id " + id);
        }
    }

    @Override
    public boolean deleteSurveyEditionById(Long id) {
        if(surveyEditionRepository.existsById(id)){
            SurveyEdition surveyEdition = surveyEditionRepository.findById(id).get();
            surveyEditionRepository.delete(surveyEdition);
            return true;
        }
        return false;
    }

    @Override
    public SurveyEditionResponseDTO updateSurveyEdition(Long id, SurveyEditionCreateDTO surveyEditionCreateDTO) {
        return null;
    }


}
