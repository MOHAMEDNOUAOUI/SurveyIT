package com.wora.api_rest_survey_it.controller;

import com.wora.api_rest_survey_it.DTO.Survey.SurveyCreateDTO;
import com.wora.api_rest_survey_it.DTO.Survey.SurveyResponseDTO;
import com.wora.api_rest_survey_it.annotation.EXIST.Exist;
import com.wora.api_rest_survey_it.entity.Survey;
import com.wora.api_rest_survey_it.mapper.SurveyMapper;
import com.wora.api_rest_survey_it.repository.SurveyRepository;
import com.wora.api_rest_survey_it.service.SurveyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public SurveyResponseDTO getSurveyById(@Exist(entity = Survey.class, repository = SurveyRepository.class)
                                               @PathVariable("id") Long id)
    {
        return surveyService.findSurveyById(id);
    }

    @GetMapping("/title/{title}")
    public SurveyResponseDTO getSurveyByTitle(@PathVariable("title") String title){
        return surveyService.findSurveyByTitle(title);
    }

    @DeleteMapping("/{surveyId}")
    public ResponseEntity<?> deleteSurvey(@PathVariable("surveyId") @Exist(entity = Survey.class, repository = SurveyRepository.class) Long id){
        if(surveyService.deleteSurveyById(id)){
            return ResponseEntity.ok().body("Survey deleted Succefully");
        }
        return ResponseEntity.notFound().build();
    }


}
