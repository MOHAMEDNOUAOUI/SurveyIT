package com.wora.api_rest_survey_it.controller;


import com.wora.api_rest_survey_it.DTO.Survey.embd.SurveyEmbdResponse;
import com.wora.api_rest_survey_it.DTO.SurveyEdition.SurveyEditionCreateDTO;
import com.wora.api_rest_survey_it.DTO.SurveyEdition.SurveyEditionResponseDTO;
import com.wora.api_rest_survey_it.annotation.EXIST.Exist;
import com.wora.api_rest_survey_it.entity.SurveyEdition;
import com.wora.api_rest_survey_it.mapper.SurveyEditionMapper;
import com.wora.api_rest_survey_it.repository.SurveyEditionRepository;
import com.wora.api_rest_survey_it.service.SurveyEditionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{surveyEditionId}")
    public SurveyEditionResponseDTO findSurveyEditionById(
            @Exist(entity = SurveyEdition.class, repository = SurveyEditionRepository.class)
            @PathVariable("surveyEditionId")
            Long id){
        return surveyEditionService.findSurveyEditionById(id);
    }

    @DeleteMapping("/{surveyEditionId}")
    public ResponseEntity<?> deleteSurveyEditionById(
            @Exist(entity = SurveyEdition.class, repository = SurveyEditionRepository.class)
            @PathVariable("surveyEditionId") Long id){
                if(surveyEditionService.deleteSurveyEditionById(id)){
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body("Survey Edition Deleted succefuly");
                }

                return ResponseEntity.noContent().build();
    }


    @PutMapping("/{surveyEditionId}")
    public SurveyEditionResponseDTO updateSurveyEdition(
            @Exist(entity = SurveyEdition.class, repository = SurveyEditionRepository.class)
            @PathVariable("surveyEditionId") Long id , @RequestBody  SurveyEditionCreateDTO surveyEditionCreateDTO ) {

        return surveyEditionService.updateSurveyEdition(id , surveyEditionCreateDTO);
    }

}
