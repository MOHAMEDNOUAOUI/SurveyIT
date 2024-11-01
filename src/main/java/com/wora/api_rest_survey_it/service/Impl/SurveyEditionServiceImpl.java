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

import java.time.Year;
import java.util.List;
import java.util.Optional;

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
//        SurveyEdition createSurveyEdition = surveyEditionMapper.toSurveyEdition(surveyEditionCreateDTO);

            SurveyEdition existSurveyEdition = surveyEditionRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Survey Edition was not found with the id " + id));

//            ValidateUpdateData(surveyEditionCreateDTO);




            SurveyEdition updatedSurveyEdition = surveyEditionRepository.save(existSurveyEdition);
            return surveyEditionMapper.convertToSurveyEditionResponseDTO(updatedSurveyEdition);



    }


//    public void ValidateUpdateData(SurveyEditionCreateDTO surveyEditionCreateDTO) {
//        if (surveyEditionCreateDTO.getSurveyId() == null && surveyEditionCreateDTO.getCreationDate() == null
//                &&surveyEditionCreateDTO.getYear() == null && surveyEditionCreateDTO.getStartDate() == null){
//            throw new RuntimeException("No data Provided to be updated , no need to update");
//        }
//    }

    public void updateSurveyEditionFields(SurveyEdition existSurveyEdition , SurveyEditionCreateDTO surveyEditionCreateDTO){

        Optional.ofNullable(surveyEditionCreateDTO.getCreationDate())
                .filter(localDate -> !localDate.equals(existSurveyEdition.getCreationDate()))
                .ifPresent(existSurveyEdition::setCreationDate);


//        Optional.ofNullable(surveyEditionCreateDTO.getYear())
//                .filter(Year -> )

        if (surveyEditionCreateDTO.getYear() != null && !surveyEditionCreateDTO.getYear().equals(existSurveyEdition.getYear())){
            existSurveyEdition.setYear(surveyEditionCreateDTO.getYear());
        }

        if (surveyEditionCreateDTO.getStartDate() != null && !surveyEditionCreateDTO.getStartDate().equals(existSurveyEdition.getStartDate())){
            existSurveyEdition.setStartDate(surveyEditionCreateDTO.getStartDate());
        }


        if (surveyEditionCreateDTO.getSurveyId()!= null && !surveyEditionCreateDTO.getSurveyId().equals(existSurveyEdition.getSurvey().getId())){
            if (surveyRepository.existsById(surveyEditionCreateDTO.getSurveyId())){
                Survey survey = surveyRepository.getReferenceById(surveyEditionCreateDTO.getSurveyId());
                existSurveyEdition.setSurvey(survey);
            }else{
                throw new EntityNotFoundException("Survey with the id " + surveyEditionCreateDTO.getSurveyId() + " was not found");
            }
        }

    }


}
