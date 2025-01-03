package com.wora.api_rest_survey_it.service.Impl;

import com.wora.api_rest_survey_it.DTO.Survey.SurveyCreateDTO;
import com.wora.api_rest_survey_it.DTO.Survey.SurveyResponseDTO;
import com.wora.api_rest_survey_it.entity.Owner;
import com.wora.api_rest_survey_it.entity.Survey;
import com.wora.api_rest_survey_it.mapper.SurveyMapper;
import com.wora.api_rest_survey_it.repository.OwnerRepository;
import com.wora.api_rest_survey_it.repository.SurveyRepository;
import com.wora.api_rest_survey_it.service.OwnerService;
import com.wora.api_rest_survey_it.service.SurveyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private SurveyMapper surveyMapper;


    @Override
    public SurveyResponseDTO saveSurvey(SurveyCreateDTO surveyCreateDTO) {
        Survey survey = surveyMapper.toSurvey(surveyCreateDTO);
        Survey surveysaved = null;
        if (surveyCreateDTO.getOwnerId() != null) {
            Owner owner = ownerRepository.findById(surveyCreateDTO.getOwnerId()).orElseThrow(() -> new RuntimeException("Owner not found"));
            survey.setOwner(owner);
            surveysaved = surveyRepository.save(survey);
        }
        return surveyMapper.toSurveyResponseDTO(surveysaved);
    }

    @Override
    public List<SurveyResponseDTO> getAllSurveys() {
        List<Survey> surveys = surveyRepository.findAll();
        if (surveys.isEmpty()) {
            throw new RuntimeException("No surveys found");
        }
        return surveys.stream().map(surveyMapper::toSurveyResponseDTO).toList();
    }

    @Override
    public SurveyResponseDTO findSurveyById(Long id) {
        if (surveyRepository.existsById(id)) {
            Survey survey = surveyRepository.findById(id).orElseThrow(() -> new RuntimeException("Survey not found"));
            return surveyMapper.toSurveyResponseDTO(survey);
        } else {
            throw new EntityNotFoundException("Could not find this survey by the id " + id);
        }
    }

    @Override
    public SurveyResponseDTO findSurveyByTitle(String title) {
        if (surveyRepository.existsByTitle(title)) {
            Survey survey = surveyRepository.findByTitle(title).get();
            return surveyMapper.toSurveyResponseDTO(survey);
        } else {
            throw new EntityNotFoundException("Thie survey with the Title " + title + " does not exist");
        }
    }

    @Override
    public boolean deleteSurveyById(Long id) {
        if (surveyRepository.existsById(id)) {
            surveyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public SurveyResponseDTO updateSurvey(Long id, SurveyCreateDTO surveyCreateDTO) {
        if (surveyRepository.existsById(id)) {

            Survey survey = surveyMapper.toSurvey(surveyCreateDTO);
            Survey existingSurvey = surveyRepository.findById(id).get();

            if (surveyCreateDTO.getOwnerId() == null && surveyCreateDTO.getTitle() == null && surveyCreateDTO.getDescription() == null) {
                throw new RuntimeException("No data provided to be Updated");
            }


            if (survey.getTitle() != null) {
                if (!survey.getTitle().equals(existingSurvey.getTitle())) {
                    existingSurvey.setTitle(survey.getTitle());
                }
            }

            if (survey.getDescription() != null) {
                if (!survey.getDescription().equals(existingSurvey.getDescription())) {
                    existingSurvey.setDescription(survey.getDescription());
                }
            }


            if (surveyCreateDTO.getOwnerId() != null) {
                if (!surveyCreateDTO.getOwnerId().equals(existingSurvey.getOwner().getId())) {
                    Optional<Owner> newOwner = ownerRepository.findById(surveyCreateDTO.getOwnerId());
                    if (newOwner.isPresent()) {
                        existingSurvey.setOwner(newOwner.get());
                    } else {
                        throw new EntityNotFoundException("Owner with the id " + surveyCreateDTO.getOwnerId() + " not found");
                    }
                }
            }

            Survey updatedSurvey = surveyRepository.save(existingSurvey);
            return surveyMapper.toSurveyResponseDTO(updatedSurvey);

        } else {
            throw new EntityNotFoundException("The Survey with the id " + id + " does not exist");
        }
    }


}
