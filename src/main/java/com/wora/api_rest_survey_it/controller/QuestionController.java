package com.wora.api_rest_survey_it.controller;


import com.wora.api_rest_survey_it.DTO.Question.QuestionCreateDTO;
import com.wora.api_rest_survey_it.DTO.Question.QuestionResponseDTO;
import com.wora.api_rest_survey_it.DTO.misendsituation.DTOResponse;
import com.wora.api_rest_survey_it.DTO.misendsituation.DTOcreateQuestionWithAnswers;
import com.wora.api_rest_survey_it.annotation.EXIST.Exist;
import com.wora.api_rest_survey_it.entity.Question;
import com.wora.api_rest_survey_it.repository.QuestionRepository;
import com.wora.api_rest_survey_it.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

//    @PostMapping
//    public QuestionResponseDTO saveQuestion(@RequestBody @Valid QuestionCreateDTO questionCreateDTO){
//        return questionService.createQuestion(questionCreateDTO);
//    }

    @PostMapping
    public DTOResponse saveQuestion(@RequestBody DTOcreateQuestionWithAnswers questionWithAnswers){
        return questionService.createWithAnswers(questionWithAnswers);
    }

    @GetMapping
    public List<QuestionResponseDTO> getAllQuestions() {
        return questionService.findAllQuestions();
    }

    @GetMapping("/{questionId}")
    public QuestionResponseDTO findOneQuestion(
            @PathVariable("questionId")
            @Exist(entity = Question.class, repository = QuestionRepository.class) Long id){
        return questionService.findQuestionById(id);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> deleteQuestion(
            @PathVariable("questionId")
            @Exist(entity = Question.class, repository = QuestionRepository.class) Long id) {
        if(questionService.deleteQuestion(id)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Question deleted succefulyy");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete this question , something happend");
    }


    @PatchMapping("/{questionId}")
    public QuestionResponseDTO updateQuestion(
            @PathVariable("questionId")
            @Exist(entity = Question.class, repository = QuestionRepository.class) Long id ,
            @RequestBody QuestionCreateDTO questionCreateDTO){
        return questionService.updateQuestion(id , questionCreateDTO);
    }
}
