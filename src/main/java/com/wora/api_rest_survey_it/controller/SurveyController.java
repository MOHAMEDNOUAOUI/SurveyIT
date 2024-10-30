package com.wora.api_rest_survey_it.controller;

import com.wora.api_rest_survey_it.DTO.Survey.SurveyCreateDTO;
import com.wora.api_rest_survey_it.DTO.Survey.SurveyResponseDTO;
import com.wora.api_rest_survey_it.entity.Survey;
import com.wora.api_rest_survey_it.mapper.SurveyMapper;
import com.wora.api_rest_survey_it.repository.SurveyRepository;
import com.wora.api_rest_survey_it.service.SurveyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;
    @Autowired
    private SurveyRepository surveyRepository;

    @PostMapping
    public SurveyResponseDTO creatSurvey(@RequestBody @Valid SurveyCreateDTO surveyCreateDTO){
        return surveyService.saveSurvey(surveyCreateDTO);
    }

    @GetMapping
    public List<SurveyResponseDTO> getAllSurveys(){
        return surveyService.getAllSurveys();
    }


}
