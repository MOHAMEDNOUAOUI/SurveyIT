package com.wora.api_rest_survey_it.controller;


import com.wora.api_rest_survey_it.DTO.SurveyEdition.SurveyEditionCreateDTO;
import com.wora.api_rest_survey_it.DTO.SurveyEdition.SurveyEditionResponseDTO;
import com.wora.api_rest_survey_it.entity.SurveyEdition;
import com.wora.api_rest_survey_it.mapper.SurveyEditionMapper;
import com.wora.api_rest_survey_it.service.SurveyEditionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("survey-edition")
public class SurveyEditionController {

    @Autowired
    private SurveyEditionService surveyEditionService;

    @Autowired
    private SurveyEditionMapper surveyEditionMapper;

    @PostMapping
    public SurveyEditionResponseDTO createSurveyEdition(@Valid @RequestBody SurveyEditionCreateDTO surveyEditionCreateDTO) {
      return surveyEditionService.saveSurveyEdition(surveyEditionCreateDTO);
    }

    @GetMapping
    public List<SurveyEditionResponseDTO> showAllSurveyEditions() {
        return surveyEditionService.getAllSurveyEditions();
    }

}